
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ForbiddenRequestException.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public abstract class ForbiddenRequestException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new forbidden request exception.
   *
   * @param message the message
   */
  public ForbiddenRequestException(String message) {
    super(message);
  }
}
