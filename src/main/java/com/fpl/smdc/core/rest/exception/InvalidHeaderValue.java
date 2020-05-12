
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidHeaderValue.
 */
public class InvalidHeaderValue extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid header value.
   *
   * @param message the message
   */
  public InvalidHeaderValue(String message) {
    super(message);
  }

}
