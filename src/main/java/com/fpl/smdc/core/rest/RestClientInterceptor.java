
package com.fpl.smdc.core.rest;

import java.io.IOException;
import java.util.Base64;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import com.fpl.smdc.util.Constants;

/**
 * The Class RestClientInterceptor.
 */
@Component
public class RestClientInterceptor implements ClientHttpRequestInterceptor {

  /** The login service api. */
  @Value("${common.logservice.application.userid}")
  private String userId;

  /** The logout service api. */
  @Value("${common.logservice.application.password}")
  private String password;

  @Autowired
  public RestClientInterceptor(@Value("${common.logservice.application.userid}") String userid,
      @Value("${common.logservice.application.userid}") String password) {
    this.userId = userid;
    this.password = password;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.
   * springframework.http.HttpRequest, byte[],
   * org.springframework.http.client.ClientHttpRequestExecution)
   */
  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {
    HttpHeaders headers = request.getHeaders();
    headers.add("correlationId", ThreadContext.get(Constants.CORRELATION_ID_STRING));
   
    headers.add("Authorization",
        ("Basic " + Base64.getEncoder().encodeToString((userId + ":" + password).getBytes())));
    return execution.execute(request, body);
  }
}
