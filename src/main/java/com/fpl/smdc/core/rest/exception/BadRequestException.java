
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class BadRequestException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public abstract class BadRequestException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new bad request exception.
   *
   * @param message the message
   */
  public BadRequestException(String message) {
    super(message);
  }
}
