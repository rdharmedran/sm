
package com.fpl.smdc.core.rest.exception;

/**
 * The Class InsufficientAccountPermissions.
 */
public class InsufficientAccountPermissions extends ForbiddenRequestException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new insufficient account permissions.
   *
   * @param message the message
   */
  public InsufficientAccountPermissions(String message) {
    super(message);
  }

}
