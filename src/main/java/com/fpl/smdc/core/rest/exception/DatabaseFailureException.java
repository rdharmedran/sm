
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class DatabaseFailureException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DatabaseFailureException extends InternalEeception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new database failure exception.
   *
   * @param message the message
   */
  public DatabaseFailureException(String message) {
    super(message);
  }
}
