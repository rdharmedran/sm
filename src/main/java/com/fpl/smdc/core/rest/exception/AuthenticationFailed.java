
package com.fpl.smdc.core.rest.exception;

/**
 * The Class AuthenticationFailed.
 */
public class AuthenticationFailed extends BadRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new authentication failed.
   *
   * @param message the message
   */
  public AuthenticationFailed(String message) {
    super(message);
  }

}
