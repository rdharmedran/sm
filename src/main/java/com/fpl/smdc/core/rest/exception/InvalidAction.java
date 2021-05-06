
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidAction.
 */
public class InvalidAction extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid action.
   *
   * @param message the message
   */
  public InvalidAction(String message) {
    super(message);
  }
}
