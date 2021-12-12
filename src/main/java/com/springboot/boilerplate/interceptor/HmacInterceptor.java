package com.springboot.boilerplate.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.boilerplate.dao.InternalClientDao;
import com.springboot.boilerplate.entity.InternalClient;
import com.springboot.boilerplate.utils.HmacCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class HmacInterceptor implements HandlerInterceptor {

  @Autowired
  InternalClientDao internalClientDao;

  @Autowired
  HmacCalculator hmacCalculator;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("Pre handle Interceptor of Hmac Interceptor for {}", request);
    String hmac = request.getHeader("hash");
    String clientName = request.getHeader("clientName");
    log.info("Hash: {}, Client Name: {}", hmac, clientName);

    try {
      if (ObjectUtils.isEmpty(hmac) || ObjectUtils.isEmpty(clientName)) {
        log.error("Hash or clientName is Blank or Empty for request {}", request.getRequestURI());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
      }

      String payload = getRequestBody(request);

      log.info("Payload pipe: {}", payload);
      if (ObjectUtils.isEmpty(payload)) {
        log.error("Blank payload for client: {}", clientName);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return false;
      }

      Optional<InternalClient> internalClientObj = internalClientDao.findByClientName(clientName);
      if (internalClientObj.isEmpty()) {
        log.error("Client not found {}", clientName);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
      }

      InternalClient internalClient = internalClientObj.get();

      if (hmacCalculator.validateClient(payload, internalClient, hmac)) {
        log.info("Hmac Verification successful for hmac Value for the hmac {} and clientName {}", hmac, clientName);
        return true;
      }
      log.info("Hmac Verification failed for hmac Value {} for the clientName {}", hmac, clientName);
    } catch (Exception th) {
      log.error("Exception occurred in validating Hmac Value for clientName {} and hmac {}. {}", clientName, hmac, th);
    }
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  public String getRequestBody(HttpServletRequest request) throws IOException {
    Map<String, Object> paramMap = getParamMap(request);
    if (!ObjectUtils.isEmpty(paramMap)) {
      return hmacCalculator.getObjectPayload(paramMap);
    }
    return null;
  }

  private Map<String, Object> getParamMap(HttpServletRequest request) throws IOException {
    InterceptorRequestWrapper interceptorRequestWrapper = new InterceptorRequestWrapper(request);
    Map<String, Object> map = new HashMap<>();
    if (HttpMethod.GET.toString().equals(interceptorRequestWrapper.getMethod())) {
      Collections.list(interceptorRequestWrapper.getParameterNames()).forEach(
          paraName -> map.put(paraName, request.getParameter(paraName)));
      return map;
    }
    String requestBody = interceptorRequestWrapper.getBody();
    if (ObjectUtils.isEmpty(requestBody)) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(requestBody, new TypeReference<>() {
    });
  }

}
