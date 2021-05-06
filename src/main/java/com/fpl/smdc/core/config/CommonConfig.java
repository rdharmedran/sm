
package com.fpl.smdc.core.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import com.fpl.smdc.core.filter.SpringLoggingFilter;
import com.fpl.smdc.core.log.LogInterceptor;
import com.fpl.smdc.util.UniqueIdGenerator;

/**
 * The Class AppConfig.
 */
@Configuration
@EnableWebMvc
public class CommonConfig extends WebMvcConfigurerAdapter {

  /** The Constant TEMPORARY_CREDENTIALS_DURATION_DEFAULT. */
  private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;


  @Autowired
  LogInterceptor logInterceptor;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
   * addInterceptors(org.springframework.web.servlet.config.annotation. InterceptorRegistry)
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(logInterceptor);
  }

  /**
   * Generator.
   *
   * @return the unique id generator
   */
  @Bean
  public UniqueIdGenerator generator() {
    return new UniqueIdGenerator();
  }

  /**
   * Logging filter.
   *
   * @return the spring logging filter
   */
  @Bean
  public SpringLoggingFilter loggingFilter() {
    return new SpringLoggingFilter();
  }
  
  @Bean("fixedThreadPool")
  public ExecutorService fixedThreadPool() {
    return Executors.newFixedThreadPool(5);
  }

  /**
   * Some filter registration.
   *
   * @return the filter registration bean
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Bean
  public FilterRegistrationBean someFilterRegistration() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(loggingFilter());
    registration.setName("loggingFilter");
    registration.setOrder(1);
    return registration;
  }
}
