
package com.fpl.smdc.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fpl.smdc.core.model.ErrorDetail;

/**
 * The Class CacheScheduler.
 */
@Service
public class CacheScheduler {

  /** The config end point. */
  @Value("${common.exception.errors.endpoint.url}")
  private String configEndPoint;

  /** The cache manager. */
  @Autowired
  CacheManager cacheManager;

  /** The rest template. */
  @Autowired
  @Qualifier("fplRestTemplate")
  RestTemplate restTemplate;

  /**
   * Inits the.
   */
  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    update();

  }

  /**
   * Update.
   */
  public void update() {



    ErrorDetail[] errors = restTemplate.getForObject(configEndPoint, ErrorDetail[].class);
    if (errors != null) {
      for (ErrorDetail error : errors) {
        if(cacheManager!=null) {
        cacheManager.getCache("errors").put(error.getErrorType(), error);
        }

      }
    }
  }
}
