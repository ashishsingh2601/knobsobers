package com.springboot.boilerplate.utils;

import com.springboot.boilerplate.dao.InternalClientDao;
import com.springboot.boilerplate.entity.InternalClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Component
public class HmacCalculator {

  @Autowired
  AesEncryption aesEncryption;

  @Autowired
  InternalClientDao internalClientDao;

  public String hmacByPayloadAndClient(Map<String, String> payload, String clientName) {
    return hmacByPayloadAndClient(getPayload(payload), clientName);
  }

  public String hmacByPayloadAndClient(String payload, String clientName) {
    String hmac = "";
    try {
      Optional<InternalClient> internalClient = internalClientDao.findByClientName(clientName);
      if (internalClient.isEmpty()) {
        log.error("Client Not Found: {}", clientName);
        return hmac;
      }
      hmac = calculateHmac(payload, aesEncryption.decrypt(internalClient.get().getSecret()));
    } catch (Exception e) {
      log.error("Exception occurred while generating hmac. {}", e.toString());
    }
    return hmac;
  }

  /**
   * Method to get piped payload.
   *
   * @param paramMap parameters
   * @return piped payload.
   */
  public String getPayload(Map<String, String> paramMap) {
    Map<String, Object> sortedMap = new TreeMap<>(paramMap);
    sortedMap.values().removeIf(Objects::isNull);
    return StringUtils.collectionToDelimitedString(sortedMap.values(), "|");
  }

  /**
   * Method to get piped payload.
   *
   * @param paramMap parameters
   * @return piped payload.
   */
  public String getObjectPayload(Map<String, Object> paramMap) {
    Map<String, Object> sortedMap = new TreeMap<>(paramMap);
    sortedMap.values().removeIf(Objects::isNull);
    return StringUtils.collectionToDelimitedString(sortedMap.values(), "|");
  }

  /**
   * Method to get piped payload.
   *
   * @param paramMap parameters
   * @return piped payload.
   */
  public String getNestedObject(Map<String, Object> paramMap) {
    Map<String, Object> newMap = new HashMap<>();
    flattenMap(paramMap, newMap);
    Map<String, Object> sortedMap = new TreeMap<>(newMap);
    sortedMap.values().removeIf(Objects::isNull);
    return StringUtils.collectionToDelimitedString(sortedMap.values(), "|");
  }

  /**
   * Method to flatten map.
   *
   * @param mapToBeUpdate parameters
   * @return map.
   */
  private void flattenMap(
      Map<String, Object> map, Map<String, Object> mapToBeUpdate
  ) {
    for (Map.Entry<String, Object> obj : map.entrySet()) {
      if (obj.getValue() instanceof LinkedHashMap) {
        flattenMap((Map<String, Object>) obj.getValue(), mapToBeUpdate);
      } else {
        mapToBeUpdate.put(obj.getKey(), obj.getValue());
      }
    }
  }

  /**
   * Converts a nested Map object to an array list of values.
   *
   * @param paramMap a nested Map object
   *                 <p>Example:
   *                 "key4" -> "val4"
   *                 "key3" -> "val3"
   *                 "key1" -> "key12" -> "val12"
   *                 "key11" -> "val11"
   *                 "key2" -> "key22" -> "val22"
   *                 "key21" -> "val21"
   *                 </p>
   * @return Piped string of values
   * <p>Example:
   * "val11|val12|val21|val22|val3|val4
   * </p>
   */
  public String getNestedPayload(Map<String, Object> paramMap) {
    Map<String, Object> sortedMap = new TreeMap<>(paramMap);
    sortedMap.values().removeIf(Objects::isNull);

    for (Map.Entry<String, Object> obj : sortedMap.entrySet()) {

      if (obj.getValue() instanceof Map) {
        sortedMap.put(obj.getKey(), getNestedPayload((Map<String, Object>) obj.getValue()));
      } else if (obj.getValue() instanceof ArrayList) {
        sortedMap = new TreeMap<>();
        List<Map<String, Object>> paramMapList = (List<Map<String, Object>>) obj.getValue();
        int count = 0;
        for (Map<String, Object> parameterMap : paramMapList) {
          for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            sortedMap.put(k + "" + count, v);
          }
          count++;
        }
      }

    }
    return StringUtils.collectionToDelimitedString(sortedMap.values(), "|");
  }

  /**
   * Pipe payload.
   *
   * @param list list.
   * @return string.
   */
  public String pipePayload(List<String> list) {
    return StringUtils.collectionToDelimitedString(list, "|");
  }

  /**
   * Method to calculate hmac.
   *
   * @param payload payload
   * @param secret  secret
   * @return string
   */
  public String calculateHmac(String payload, String secret) {
    SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    Mac mac = null;
    try {
      mac = Mac.getInstance("HmacSHA256");
      mac.init(keySpec);
      byte[] result = mac.doFinal(payload.getBytes());
      return Base64.getEncoder().encodeToString(result);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new RuntimeException("Exception hashing payload", e);
    }
  }

  /**
   * Validate client.
   *
   * @param payload        payload
   * @param internalClient client
   * @param hmacValue      hmac
   * @return boolean
   */
  public boolean validateClient(String payload, InternalClient internalClient, String hmacValue) {
    String clientKey = this.aesEncryption.decrypt(internalClient.getSecret());
    return this.validateHmac(payload, clientKey, hmacValue);
  }

  /**
   * Validate client.
   *
   * @param payload   payload
   * @param secret    secret
   * @param hmacValue hmac
   * @return boolean
   */
  public boolean validateHmac(String payload, String secret, String hmacValue) {
    try {
      String calculatedHmac = calculateHmac(payload, secret);
      if (!ObjectUtils.isEmpty(calculatedHmac) && calculatedHmac.equalsIgnoreCase(hmacValue)) {
        log.info("Hmac validated {} and hmac Value {}", calculatedHmac, hmacValue);
        return true;
      }

      String calculateHexHmac = calculateHmacHexEncoded(payload, secret);

      if (!ObjectUtils.isEmpty(calculateHexHmac) && calculateHexHmac.equalsIgnoreCase(hmacValue)) {
        log.info("Hmac validated {} and hmac Value {}", calculatedHmac, hmacValue);
        return true;
      }

      log.info("Mismatch of Calculated Hmac Value {} and hmac Value {} and payload: {}",
          calculatedHmac, hmacValue, payload);
    } catch (Exception ex) {
      log.info("Exception in validating client Hmac for payload :{}, {}", payload, ex);
    }
    return false;
  }

  /**
   * Calculate hex hmac.
   *
   * @param payload payload
   * @param secret  secret
   * @return string
   */
  public String calculateHmacHexEncoded(String payload, String secret) {
    SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    Mac mac = null;
    try {
      mac = Mac.getInstance("HmacSHA256");
      mac.init(keySpec);
      byte[] hash = mac.doFinal(payload.getBytes());

      StringBuilder result = new StringBuilder();
      for (byte b : hash) {
        result.append(String.format("%02x", b));
      }

      return result.toString();

    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new RuntimeException("Exception hashing payload", e);
    }
  }
}
