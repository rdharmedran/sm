
package com.fpl.smdc.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class ErrorDetail.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "error_id", "error_type", "error_code", "error_msg", "status_type" })

public class ErrorDetail {

  /** The error id. */
  @JsonProperty("error_id")
  private Integer errorId;

  /** The error type. */
  @JsonProperty("error_type")
  private String errorType;

  /** The error code. */
  @JsonProperty("error_code")
  private Integer errorCode;

  /** The error msg. */
  @JsonProperty("error_msg")
  private String errorMsg;

  /** The status type. */
  @JsonProperty("status_type")
  private String statusType;

  /**
   * Instantiates a new error detail.
   */
  public ErrorDetail() {}

  /**
   * Instantiates a new error detail.
   *
   * @param errorId the error id
   * @param errorType the error type
   * @param errorCode the error code
   * @param errorMsg the error msg
   * @param statusType the status type
   */
  public ErrorDetail(Integer errorId, String errorType, Integer errorCode, String errorMsg,
      String statusType) {
    super();
    this.errorId = errorId;
    this.errorType = errorType;
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
    this.statusType = statusType;
  }

  /**
   * Gets the error code.
   *
   * @return the error code
   */
  @JsonProperty("error_code")
  public Integer getErrorCode() {
    return errorCode;
  }

  /**
   * Gets the error id.
   *
   * @return the error id
   */
  @JsonProperty("error_id")
  public Integer getErrorId() {
    return errorId;
  }

  /**
   * Gets the error msg.
   *
   * @return the error msg
   */
  @JsonProperty("error_msg")
  public String getErrorMsg() {
    return errorMsg;
  }

  /**
   * Gets the error type.
   *
   * @return the error type
   */
  @JsonProperty("error_type")
  public String getErrorType() {
    return errorType;
  }

  /**
   * Gets the status type.
   *
   * @return the status type
   */
  @JsonProperty("status_type")
  public String getStatusType() {
    return statusType;
  }

  /**
   * Sets the error code.
   *
   * @param errorCode the new error code
   */
  @JsonProperty("error_code")
  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Sets the error id.
   *
   * @param errorId the new error id
   */
  @JsonProperty("error_id")
  public void setErrorId(Integer errorId) {
    this.errorId = errorId;
  }

  /**
   * Sets the error msg.
   *
   * @param errorMsg the new error msg
   */
  @JsonProperty("error_msg")
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  /**
   * Sets the error type.
   *
   * @param errorType the new error type
   */
  @JsonProperty("error_type")
  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  /**
   * Sets the status type.
   *
   * @param statusType the new status type
   */
  @JsonProperty("status_type")
  public void setStatusType(String statusType) {
    this.statusType = statusType;
  }

}
