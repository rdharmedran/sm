
package com.fpl.smdc.core.rest.exception;

/**
 * The Class OperationTimedOut.
 */
public class OperationTimedOut extends InternalEeception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new operation timed out.
   *
   * @param message the message
   */
  public OperationTimedOut(String message) {
    super(message);
  }

}
