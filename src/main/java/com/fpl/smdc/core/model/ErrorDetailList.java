
package com.fpl.smdc.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ErrorDetailList.
 */
public class ErrorDetailList {

  /** The errors. */
  private List<ErrorDetail> errors;

  /**
   * Instantiates a new error detail list.
   */
  public ErrorDetailList() {
    errors = new ArrayList<>();
  }

  /**
   * Gets the errors.
   *
   * @return the errors
   */
  public List<ErrorDetail> getErrors() {
    return errors;
  }

  /**
   * Sets the errors.
   *
   * @param errors the new errors
   */
  public void setErrors(List<ErrorDetail> errors) {
    this.errors = errors;
  }

}
