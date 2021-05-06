
package com.fpl.smdc.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fpl.smdc.util.UniqueIdGenerator;

/**
 * The Class SpringLoggingFilter.
 */
@Component
public class SpringLoggingFilter implements Filter {

  /** The dashboard id. */
  @Value("${common.logservice.application.id}")
  private String dashboardId;

  /** The generator. */
  @Autowired
  private UniqueIdGenerator generator;

  /** The ignore patterns. */
  @Value("${common.logservice.url.ignore}")
  private String ignorePatterns;

  /*
   * (non-Javadoc)
   *
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
   * javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      if (ignorePatterns != null && httpRequest.getRequestURI().matches(ignorePatterns)) {
        chain.doFilter(request, response);
      } else {

        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(httpRequest);
        generator.getOrGenerateUniqueId(mutableRequest);

        chain.doFilter(mutableRequest, response);

      }
    }

  }

}
