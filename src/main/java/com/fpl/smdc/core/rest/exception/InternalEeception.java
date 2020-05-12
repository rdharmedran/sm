
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class InternalEeception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalEeception extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The http status code. */
  public static Integer HTTP_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

  /**
   * Instantiates a new internal eeception.
   *
   * @param message the message
   */
  public InternalEeception(String message) {
    super(message);
  }
}
