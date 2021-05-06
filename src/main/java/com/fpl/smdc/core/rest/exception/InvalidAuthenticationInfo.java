
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InvalidAuthenticationInfo.
 */
public class InvalidAuthenticationInfo extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new invalid authentication info.
   *
   * @param message the message
   */
  public InvalidAuthenticationInfo(String message) {
    super(message);
  }
}
