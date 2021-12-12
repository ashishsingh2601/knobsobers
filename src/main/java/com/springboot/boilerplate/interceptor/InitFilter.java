package com.springboot.boilerplate.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class InitFilter implements Filter {

  @Value("${spring.profiles.active:dev}")
  private String activeProfile;

  /**
   * Casts request to interceptorRequestWrapper object and run interceptors.
   *
   * @param request  request
   * @param response response
   * @param chain    chain
   * @throws IOException      throws IO Exception
   * @throws ServletException throws ServletException
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    long startTime = System.currentTimeMillis();

    if("dev".equals(activeProfile)) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      httpResponse.setHeader("Access-Control-Allow-Origin", "*");
      httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
      httpResponse.setHeader("Access-Control-Allow-Headers", "*");
      httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
      httpResponse.setHeader("Access-Control-Max-Age", "180");
      if ("OPTIONS".equals(httpRequest.getMethod())) {
        httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        return;
      }
    }

    chain.doFilter(request, response);
    long timeTaken = System.currentTimeMillis() - startTime;
    String uri = ((HttpServletRequest) request).getRequestURI();
    log.info("Time taken by API: {} is {}", uri, timeTaken);
  }

}

