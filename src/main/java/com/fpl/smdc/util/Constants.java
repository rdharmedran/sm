
package com.fpl.smdc.util;

/**
 * The Class Constants.
 */
public class Constants {
  // headers

  /** The Constant CORRELATION_ID_STRING. */
  public static final String CORRELATION_ID_STRING = "X-Correlation-Id";

  /** The Constant TRACEABILITY_ID_STRING. */
  public static final String TRACEABILITY_ID_STRING = "X-Traceability-Id";

  /** The Constant USER_ID_STRING. */
  public static final String USER_ID_STRING = "user_id";

  /** The Constant CLIENT_ID_STRING. */
  public static final String CLIENT_ID_STRING = "client_id";

  /** The Constant SCOPE_CLIENT_ID_STRING. */
  public static final String SCOPE_CLIENT_ID_STRING = "scope_client_id";

  /** The Constant SCOPE_STRING. */
  public static final String SCOPE_STRING = "scope";

  /** The Constant ENDPOINT_STRING. */
  public static final String ENDPOINT_STRING = "endpoint";

  /** The Constant SWAGGER_OPERATION_STRING. */
  // Swagger 2.0 operation header name
  public static final String SWAGGER_OPERATION_STRING = "swagger_operation";

  /** The Constant OPENAPI_OPERATION_STRING. */
  // OpenAPI 3.0 operation header name
  public static final String OPENAPI_OPERATION_STRING = "openapi_operation";

  /** The Constant SCOPE_TOKEN_STRING. */
  public static final String SCOPE_TOKEN_STRING = "X-Scope-Token";

  /** The Constant CONSUL_TOKEN_STRING. */
  public static final String CONSUL_TOKEN_STRING = "X-Consul-Token";

  /** The Constant AUDIT_LOGGER. */
  // Logger
  public static final String AUDIT_LOGGER = "Audit";

  /** The Constant FRAMEWORK_NAME. */
  // Framework
  public static final String FRAMEWORK_NAME = "light";

  /** The Constant METHOD_CONFIG_PREFIX. */
  public static final String METHOD_CONFIG_PREFIX = "methodconfig.";

  /** The Constant REGISTRY_HEARTBEAT_SWITCHER. */
  // Switcher
  public static final String REGISTRY_HEARTBEAT_SWITCHER = "RegistryHeartBeat";

  /** The Constant MILLS. */
  public static final int MILLS = 1;

  /** The Constant SECOND_MILLS. */
  public static final int SECOND_MILLS = 1000;

  /** The Constant MINUTE_MILLS. */
  public static final int MINUTE_MILLS = 60 * SECOND_MILLS;

  /** The Constant DEFAULT_VERSION. */
  public static final String DEFAULT_VERSION = "1.0";

  /** The Constant DEFAULT_VALUE. */
  public static final String DEFAULT_VALUE = "default";

  /** The Constant DEFAULT_INT_VALUE. */
  public static final int DEFAULT_INT_VALUE = 0;

  /** The Constant DEFAULT_CHARACTER. */
  public static final String DEFAULT_CHARACTER = "utf-8";

  /** The Constant NODE_TYPE_SERVICE. */
  public static final String NODE_TYPE_SERVICE = "service";

  /** The Constant PROTOCOL_SEPARATOR. */
  public static final String PROTOCOL_SEPARATOR = "://";

  /** The Constant PROTOCOL_LIGHT. */
  public static final String PROTOCOL_LIGHT = "light";

  /** The Constant TAG_ENVIRONMENT. */
  public static final String TAG_ENVIRONMENT = "environment";

  /** The Constant PATH_SEPARATOR. */
  public static final String PATH_SEPARATOR = "/";

  /** The Constant REGISTRY_PROTOCOL_LOCAL. */
  public static final String REGISTRY_PROTOCOL_LOCAL = "local";

  /** The Constant REGISTRY_PROTOCOL_DIRECT. */
  public static final String REGISTRY_PROTOCOL_DIRECT = "direct";

  /** The Constant REGISTRY_PROTOCOL_ZOOKEEPER. */
  public static final String REGISTRY_PROTOCOL_ZOOKEEPER = "zookeeper";

  /** The Constant ZOOKEEPER_REGISTRY_NAMESPACE. */
  public static final String ZOOKEEPER_REGISTRY_NAMESPACE = "/light";

  /** The Constant ZOOKEEPER_REGISTRY_COMMAND. */
  public static final String ZOOKEEPER_REGISTRY_COMMAND = "/command";

}
