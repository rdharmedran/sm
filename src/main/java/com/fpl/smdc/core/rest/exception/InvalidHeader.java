
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidHeader.
 */
public class InvalidHeader extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid header.
   *
   * @param message the message
   */
  public InvalidHeader(String message) {
    super(message);
  }

}
