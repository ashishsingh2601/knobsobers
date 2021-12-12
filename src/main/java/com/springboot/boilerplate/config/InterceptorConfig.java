package com.springboot.boilerplate.config;

import com.springboot.boilerplate.interceptor.MerchantTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

  @Autowired
  MerchantTokenInterceptor merchantTokenInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(merchantTokenInterceptor)
        .excludePathPatterns("/");
  }
}
