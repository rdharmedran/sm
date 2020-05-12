
package com.fpl.smdc.core.rest.exception.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class ErrorResponse.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "path", "error_code", "error_msg", "status_code" })
public class ErrorResponse {

  /** The correlation id. */
  @JsonProperty("correlation_id")
  private String correlationId;

  /** The path. */
  @JsonProperty("path")
  private String path;

  /** The status code. */
  @JsonProperty("status")
  private String statusCode;

  /** The error code. */
  @JsonProperty("error_code")
  private Integer errorCode;

  /** The error msg. */
  @JsonProperty("message")
  private String errorMsg;

  /** The additional properties. */
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * Instantiates a new error response.
   */
  public ErrorResponse() {}

  /**
   * Instantiates a new error response.
   *
   * @param i the i
   * @param j the j
   * @param errorMsg the error msg
   * @param details the details
   * @param path the path
   */
  public ErrorResponse(String i, int j, String errorMsg, List<String> details, String path) {
    super();
    this.statusCode = i;
    this.errorCode = j;
    this.errorMsg = errorMsg;
    this.path = path;
  }

  /**
   * Instantiates a new error response.
   *
   * @param i the i
   * @param j the j
   * @param errorMsg the error msg
   * @param details the details
   * @param path the path
   * @param correlationId the correlation id
   */
  public ErrorResponse(String i, int j, String errorMsg, List<String> details, String path,
      String correlationId) {
    super();
    this.statusCode = i;
    this.errorCode = j;
    this.errorMsg = errorMsg;
    this.path = path;
    this.correlationId = correlationId;
  }

  /**
   * Gets the additional properties.
   *
   * @return the additional properties
   */
  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  /**
   * Gets the correlation id.
   *
   * @return the correlation id
   */
  @JsonProperty("correlation_id")
  public String getCorrelationId() {
    return correlationId;
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
   * Gets the error msg.
   *
   * @return the error msg
   */
  @JsonProperty("message")
  public String getErrorMsg() {
    return errorMsg;
  }

  /**
   * Gets the path.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * Gets the status code.
   *
   * @return the status code
   */
  @JsonProperty("status")
  public String getStatusCode() {
    return statusCode;
  }

  /**
   * Sets the additional property.
   *
   * @param name the name
   * @param value the value
   */
  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  /**
   * Sets the correlation id.
   *
   * @param correlationId the new correlation id
   */
  @JsonProperty("correlation_id")
  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
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
   * Sets the error msg.
   *
   * @param errorMsg the new error msg
   */
  @JsonProperty("message")
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  /**
   * Sets the path.
   *
   * @param path the new path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * Sets the status code.
   *
   * @param statusCode the new status code
   */
  @JsonProperty("status")
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

}
