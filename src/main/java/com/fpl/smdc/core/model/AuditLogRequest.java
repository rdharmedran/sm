
package com.fpl.smdc.core.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class AuditLogRequest.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dashboardName", "userName",

    "clientIp" })
public class AuditLogRequest {

  /** The dasboard id. */
  @JsonProperty("dashboardName")
  private String dasboardId;

  /** The user name. */
  @JsonProperty("userName")
  private String userName;

  /** The client ip. */
  @JsonProperty("clientIp")
  private String clientIp;

  /** The additional properties. */
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * Instantiates a new audit log request.
   */
  public AuditLogRequest() {

  }

  /**
   * Instantiates a new audit log request.
   *
   * @param dasboardId the dasboard id
   * @param username the username
   * @param clientIp the client ip
   */
  public AuditLogRequest(String dasboardId, String username, String clientIp) {
    super();
    this.dasboardId = dasboardId;
    this.userName = username;

    this.clientIp = clientIp;
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
   * Gets the client ip.
   *
   * @return the client ip
   */
  @JsonProperty("clientIp")
  public String getClientIp() {
    return clientIp;
  }

  /**
   * Gets the dasboard id.
   *
   * @return the dasboard id
   */
  @JsonProperty("dashboardName")
  public String getDasboardId() {
    return dasboardId;
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  @JsonProperty("userName")
  public String getUserName() {
    return userName;
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
   * Sets the client ip.
   *
   * @param clientIp the new client ip
   */
  @JsonProperty("clientIp")
  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  /**
   * Sets the dasboard id.
   *
   * @param dasboardId the new dasboard id
   */
  @JsonProperty("dashboardName")
  public void setDasboardId(String dasboardId) {
    this.dasboardId = dasboardId;
  }

  /**
   * Sets the user name.
   *
   * @param username the new user name
   */
  @JsonProperty("userName")
  public void setUserName(String username) {
    this.userName = username;
  }

}
