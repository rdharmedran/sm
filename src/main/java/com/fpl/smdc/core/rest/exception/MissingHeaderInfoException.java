
package com.fpl.smdc.core.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class MissingHeaderInfoException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingHeaderInfoException extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new missing header info exception.
   *
   * @param message the message
   */
  public MissingHeaderInfoException(String message) {
    super(message);
  }
}
