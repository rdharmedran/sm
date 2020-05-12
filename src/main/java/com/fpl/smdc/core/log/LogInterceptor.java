
package com.fpl.smdc.core.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class LogInterceptor.
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

  /** The logging service. */
  @Autowired
  LoggingService loggingService;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
   * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * java.lang.Exception)
   */
  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    // loggingService.logResponse(httpServletRequest, httpServletResponse, o, e);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.
   * http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * org.springframework.web.servlet.ModelAndView)
   */
  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {
    loggingService.logResponse(httpServletRequest, httpServletResponse, o, null);

  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.
   * http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    loggingService.logRequest(request);

    return true;
  }
}
