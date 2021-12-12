package com.springboot.boilerplate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
public class RestUtil {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final MapperUtil mapperUtil;

  @Autowired
  public RestUtil(RestTemplate restTemplate, MapperUtil mapperUtil) {
    this.restTemplate = restTemplate;
    this.mapperUtil = mapperUtil;
    this.objectMapper = mapperUtil.objectMapper;
  }

  private HttpEntity<?> getHeader(Map<String, String> headers) {
    HttpHeaders header = new HttpHeaders();
    headers.entrySet().forEach(entry -> {
      header.set(entry.getKey(), entry.getValue());
    });
    return new HttpEntity<>(header);
  }


  public <T> T getForObject(
      String url,
      Map<String, String> headers,
      Map<String, String> queryParams,
      Class<T> clazz
  ) throws Exception {
    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
    queryParams.forEach(uriComponentsBuilder::queryParam);
    return getForObject(uriComponentsBuilder.toUriString(), headers, clazz);
  }

  public <T> T getForObject(String url, Map<String, String> headers, Class<T> clazz) throws Exception {
    HttpEntity<?> request = getHeader(headers);
    UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();

    try {
      log.info("Url : {}, Request : {}", url, mapperUtil.jsonStringify(request));
      long startTime = System.currentTimeMillis();
      ResponseEntity<String> response = restTemplate.exchange(uriComponents.encode().toUri(), HttpMethod.GET, request, String.class);
      log.info("Response Time for Url:{} is {}ms", uriComponents.encode().toUri(), System.currentTimeMillis() - startTime);
      log.info("Response : {}", mapperUtil.jsonStringify(response));
      if (HttpStatus.OK.equals(response.getStatusCode())) {
        return objectMapper.readValue(response.getBody(), clazz);
      }
      throw new RestClientException(response.getStatusCode().value() + "");
    } catch (RestClientException e) {
      log.error("Rest API Exception: {},{}", e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Error : {}, {}", e.getMessage(), e);
      throw e;
    }

  }

}
