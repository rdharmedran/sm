
package com.fpl.smdc.util;

import java.util.UUID;
import org.apache.logging.log4j.ThreadContext;
import org.jboss.logging.MDC;
import com.fpl.smdc.core.filter.MutableHttpServletRequest;

/**
 * The Class UniqueIdGenerator.
 */
public class UniqueIdGenerator {

  /**
   * Gets the or generate unique id.
   *
   * @param request the request
   * @return the or generate unique id
   */
  public void getOrGenerateUniqueId(MutableHttpServletRequest request) {
    MDC.clear();
    String correlationId = request.getHeader(Constants.CORRELATION_ID_STRING);
    if (correlationId == null) {
      correlationId = UUID.randomUUID().toString();
      request.putHeader(Constants.CORRELATION_ID_STRING, correlationId);
    }
    MDC.put(Constants.CORRELATION_ID_STRING, correlationId);
    ThreadContext.put(Constants.CORRELATION_ID_STRING, correlationId);
  }

}
