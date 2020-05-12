
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidParameterValue.
 */
public class InvalidParameterValue extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid parameter value.
   *
   * @param message the message
   */
  public InvalidParameterValue(String message) {
    super(message);
  }
}
