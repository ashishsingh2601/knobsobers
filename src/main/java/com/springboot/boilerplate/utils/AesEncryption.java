package com.springboot.boilerplate.utils;

import com.springboot.boilerplate.dao.MasterKeyDao;
import com.springboot.boilerplate.entity.MasterKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;

@Slf4j
@Component
public class AesEncryption {

  @Autowired
  MasterKeyDao masterKeyDao;

  @Value("${super.key}")
  String superKey;

  private static final String INIT_VECTOR = "encryptionIntVec";
  private String masterSecret = null;

  @PostConstruct
  private void loadMasterKey() {
    MasterKey masterKey = masterKeyDao.findByActiveIsTrue();
    if (!ObjectUtils.isEmpty(masterKey)) {
      masterSecret = decrypt(superKey, masterKey.getSecret());
      log.info("Master key loaded successfully");
      return;
    }
    log.error("Master key loading failed");
  }

  /**
   * Method to get master key string.
   *
   * @return master key
   */
  public String getMasterKey() {
    if (ObjectUtils.isEmpty(masterSecret)) {
      loadMasterKey();
    }
    return masterSecret;
  }

  /**
   * Method to decrypt.
   *
   * @param value string to be decrypted.
   * @return decrypted string.
   */
  public String decrypt(String value) {
    return decrypt(masterSecret, value);
  }

  /**
   * Method to decrypt.
   *
   * @param key   string to decrypt with.
   * @param value string to be decrypted.
   * @return decrypted string.
   */
  public String decrypt(String key, String value) {
    return decrypt(key, value, INIT_VECTOR);
  }

  /**
   * Method to decrypt.
   *
   * @param initVector init vector.
   * @param key        string to decrypt with.
   * @param value      string to be decrypted.
   * @return decrypted string.
   */
  public String decrypt(String key, String value, String initVector) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
      return new String(cipher.doFinal(Base64.getDecoder().decode(value.getBytes())));
    } catch (Exception ex) {
      log.error("Exception in decryption: ", ex);
    }
    return null;
  }

  public String encrypt(String value) {
    return encrypt(masterSecret, value);
  }

  public String encrypt(String key, String value) {
    return encrypt(key, value, INIT_VECTOR);
  }

  /**
   * Method to encrypt.
   *
   * @param key        key
   * @param value      value
   * @param initVector init vector
   * @return encrypted string
   */
  public String encrypt(String key, String value, String initVector) {
    try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
      byte[] encrypted = cipher.doFinal(value.getBytes());
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception ex) {
      log.error("Exception in encryption: " + ex);
    }
    return null;
  }

  /**
   * Key.
   *
   * @return string
   */
  public String generateAesKey() {
    return generateAesKey(32);
  }

  /**
   * Method to generate key.
   *
   * @param length length of key.
   * @return string
   */
  public String generateAesKey(int length) {
    try {
      String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lower = upper.toLowerCase(Locale.ROOT);
      String digits = "0123456789";
      String alphanum = upper + lower + digits;
      SecureRandom rnd = new SecureRandom();

      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        sb.append(alphanum.charAt(rnd.nextInt(alphanum.length())));
      }
      return sb.toString();

    } catch (Exception e) {
      log.error("Exception in generating ");
    }
    return null;
  }

  public static String getBase64Value(String value) {
    return Base64.getEncoder().encodeToString(value.getBytes());
  }

}
