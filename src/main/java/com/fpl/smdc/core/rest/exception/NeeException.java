
package com.fpl.smdc.core.rest.exception;

/**
 * The Class NeeException.
 */
public abstract class NeeException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new nee exception.
   *
   * @param message the message
   */
  public NeeException(String message) {
    super(message);
  }
}
