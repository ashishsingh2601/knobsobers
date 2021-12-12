package com.springboot.boilerplate.interceptor;

import com.springboot.boilerplate.interservice.merchant.MerchantService;
import com.springboot.boilerplate.interservice.merchant.dto.BasicMerchantDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MerchantTokenInterceptor implements HandlerInterceptor {

  @Autowired
  MerchantService merchantService;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler
  ) {
    log.info("Request url {}", request.getRequestURI());

    String token = request.getHeader("token");

    log.info("Request with token {}", token);
    try {
      if (ObjectUtils.isEmpty(token)) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        log.error("Merchant token is missing.");
        return false;
      }

      BasicMerchantDetailsDto merchant = merchantService.fetchMerchantDetails(token);
      if (ObjectUtils.isEmpty(merchant)) {
        log.info("Merchant token is invalid.");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
      }
      String ip = request.getHeader("True-Client-IP");
      if (StringUtils.isEmpty(ip)) {
        ip = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
          ip = request.getRemoteAddr();
        }
      }
      log.info("Merchant found id:{}, ip:{}", merchant.getId(), ip);
      request.setAttribute("merchant", merchant);
      request.setAttribute("token", token);
      return true;
    } catch (Throwable th) {
      log.error("Exception occurred in Token Validation Interceptor : {}", th);
    }
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }
}
