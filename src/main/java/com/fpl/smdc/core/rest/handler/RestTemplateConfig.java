
package com.fpl.smdc.core.rest.handler;

import java.util.Collections;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import com.fpl.smdc.core.rest.RestClientInterceptor;

/**
 * The Class RestTemplateConfig.
 */
@Configuration
public class RestTemplateConfig {

  /** The http client. */
  @Autowired
  CloseableHttpClient httpClient;
  /** The login service api. */
  @Value("${common.logservice.application.userid}")
  private String userId;

  /** The logout service api. */
  @Value("${common.logservice.application.password}")
  private String password;

  /**
   * Client http request factory.
   *
   * @return the http components client http request factory
   */
  @Bean
  public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setHttpClient(httpClient);
    return clientHttpRequestFactory;
  }

  /**
   * Rest template.
   *
   * @return the rest template
   */
  @Bean(name="fplRestTemplate")
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
    restTemplate.setInterceptors(Collections.singletonList(new RestClientInterceptor(userId,password)));
    return restTemplate;
  }

  /**
   * Task scheduler.
   *
   * @return the task scheduler
   */
  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setThreadNamePrefix("poolScheduler");
    scheduler.setPoolSize(50);
    return scheduler;
  }
}
