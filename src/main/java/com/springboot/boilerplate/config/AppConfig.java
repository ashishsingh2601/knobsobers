package com.springboot.boilerplate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.boilerplate.interservice.merchant.MerchantService;
import com.springboot.boilerplate.interservice.merchant.MerchantServiceImpl;
import com.springboot.boilerplate.utils.HmacCalculator;
import com.springboot.boilerplate.utils.MapperUtil;
import com.springboot.boilerplate.utils.RestUtil;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

  @Autowired
  HmacCalculator hmacCalculator;

  @Value("${http.max.total:10}")
  private int maxTotal;

  @Value("${http.max.per.route:4}")
  private int defaultMaxPerRoute;

  @Value("${http.connection.request.timeout:5000}")
  private int connectionRequestTimeout;

  @Value("${http.connect.timeout:10000}")
  private int connectTimeout;

  @Value("${http.read.timeout:45000}")
  private int readTimeout;

  @Value("${http.idle.connection.timeout:60000}")
  private int idleConnectionTimeout;

  @Value("${http.default.keep.alive:20000}")
  private int defaultKeepAliveTime;


  @Bean
  public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
    return new ConnectionKeepAliveStrategy() {
      @Override
      public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        HeaderElementIterator it = new BasicHeaderElementIterator
            (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (it.hasNext()) {
          HeaderElement he = it.nextElement();
          String param = he.getName();
          String value = he.getValue();

          if (value != null && param.equalsIgnoreCase("timeout")) {
            return Long.parseLong(value) * 1000;
          }
        }
        return defaultKeepAliveTime;
      }
    };
  }

  @Bean
  public RestTemplate restTemplate() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(maxTotal);
    connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

    CloseableHttpClient httpClient = HttpClientBuilder
        .create()
        .setConnectionManager(connectionManager)
        .setKeepAliveStrategy(connectionKeepAliveStrategy())
        .evictIdleConnections(idleConnectionTimeout, TimeUnit.MILLISECONDS)
        .build();

    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    factory.setConnectionRequestTimeout(connectionRequestTimeout);
    factory.setConnectTimeout(connectTimeout);
    factory.setReadTimeout(readTimeout);
    return new RestTemplate(factory);
  }

  @Bean
  MapperUtil mapperUtil() {
    return new MapperUtil(new ObjectMapper());
  }

  @Bean
  RestUtil restUtils() {
    return new RestUtil(restTemplate(), mapperUtil());
  }

  @Bean
  MerchantService merchantService() {
    return new MerchantServiceImpl(mapperUtil(), restUtils(), hmacCalculator);
  }
}