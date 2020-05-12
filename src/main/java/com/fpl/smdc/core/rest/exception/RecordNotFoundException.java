
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class RecordNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends NeeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new record not found exception.
   *
   * @param message the message
   */
  public RecordNotFoundException(String message) {
    super(message);
  }
}
