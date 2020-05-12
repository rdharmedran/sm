
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidUri.
 */
public class InvalidUri extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid uri.
   *
   * @param message the message
   */
  public InvalidUri(String message) {
    super(message);
  }

}
