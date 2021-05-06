
package com.fpl.smdc.core.log;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpl.smdc.core.model.AuditLogRequest;
import com.fpl.smdc.core.model.JobStatus;
import com.fpl.smdc.util.Constants;

/**
 * The Class LoggingService.
 */
@Component
public class LoggingService {

  /** The Constant APPLICATION_JSON. */
  private static final String APPLICATION_JSON = "application/json";

  /** The Constant CONTENT_TYPE. */
  private static final String CONTENT_TYPE = "Content-Type";

  /** The Constant LOG. */
  private static final Logger LOG = LogManager.getLogger(LoggingService.class);

  /**
   * To base 64.
   *
   * @param str the str
   * @return the string
   */
  @NotNull
  public static String toBase64(@NotNull String str) {
    return Base64.getEncoder().encodeToString(str.getBytes());
  }

  /** The executor service. */
  @Autowired
  @Qualifier("fixedThreadPool")
  private ExecutorService executorService;

  /** The login service api. */
  @Value("${common.logservice.login.endpoint.url}")
  private String loginServiceApi;

  /** The logout service api. */
  @Value("${common.logservice.logout.endpoint.url}")
  private String logoutServiceApi;

  /** The job status service api. */
  @Value("${common.logservice.jobs.status.endpoint.url}")
  private String jobStatusServiceApi;

  /** The dashboard id. */
  @Value("${common.logservice.application.id}")
  private String dashboardId;

  /** The rest template. */
  @Autowired
  @Qualifier("fplRestTemplate")
  RestTemplate restTemplate;

  /** The object mapper. */
  @Autowired
  ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Gets the user.
   *
   * @param request the request
   * @return the user
   */
  private String getUser(HttpServletRequest request) {
    String username = "anonymous";
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.toLowerCase().startsWith("basic")) {
      String base64Credentials = authHeader.substring("Basic".length()).trim();
      byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
      String credentials = new String(credDecoded, StandardCharsets.UTF_8);
      final String[] values = credentials.split(":", 2);
      if (values.length < 2) {
        return null;
      }
      username = values[0];
      LOG.info("user: {}", values[0]);
    } else {
      if (SecurityContextHolder.getContext() != null
          && SecurityContextHolder.getContext().getAuthentication() != null) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          username = ((UserDetails) principal).getUsername();
        } else if (principal != null) {
          username = principal.toString();
        }
      }
    }
    return username;
  }

  /**
   * Log error.
   *
   * @param request the request
   * @param e the e
   */
  public void logError(HttpServletRequest request, Exception e) {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        // e.printStackTrace();
        // LOG.error( ExceptionUtils.getStackTrace(e));
        LOG.error(
            "Timestamp: {} - CorrelationId: {} - UserId: {} - ApplicationId: {} - Response Status: {} - Error Detail: {} ",
            new Date(), request.getHeader(Constants.CORRELATION_ID_STRING), getUser(request),
            dashboardId, 500, ExceptionUtils.getStackTrace(e));

        return "";

      }
    };
    executorService.submit(callable);

  }

  /**
   * Login.
   *
   * @param msg the msg
   */
  public void login(AuditLogRequest msg) {

    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {

        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        HttpEntity<AuditLogRequest> httpEntity = new HttpEntity<>(msg, headers);

        restTemplate.exchange(loginServiceApi, HttpMethod.POST, httpEntity, String.class);

        return msg.toString();

      }
    };
    executorService.submit(callable);
  }

  /**
   * Log job status.
   *
   * @param status the status
   * @return the job status
   */
  public JobStatus logJobStatus(JobStatus status) {
    Callable<JobStatus> callable = new Callable<JobStatus>() {
      @Override
      public JobStatus call() throws Exception {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        HttpEntity<JobStatus> httpEntity = new HttpEntity<>(status, headers);

        return restTemplate
            .exchange(jobStatusServiceApi, HttpMethod.POST, httpEntity, JobStatus.class).getBody();

      }
    };
    executorService.submit(callable);
    return status;

  }

  /**
   * Logout.
   *
   * @param msg the msg
   */
  public void logout(AuditLogRequest msg) {

    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {

        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        HttpEntity<AuditLogRequest> httpEntity = new HttpEntity<>(msg, headers);

        restTemplate.exchange(loginServiceApi, HttpMethod.POST, httpEntity, String.class);

        return msg.toString();

      }
    };
    executorService.submit(callable);
  }

  /**
   * Log request.
   *
   * @param request the request
   */
  public void logRequest(HttpServletRequest request) {

      LOG.debug(
          "Request Details: Timestamp: {} - CorrelationId: {} - UserId: {} - ApplicationId: {} - Request URI: {} - Http Method: {} ",
          new Date(), request.getHeader(Constants.CORRELATION_ID_STRING), getUser(request),
          dashboardId, request.getRequestURI(), request.getMethod());


  }

  /**
   * Log response.
   *
   * @param request the request
   * @param httpResponse the http response
   * @param object the object
   * @param e the e
   */
  public void logResponse(HttpServletRequest request, HttpServletResponse httpResponse,
      Object object, Exception e) {

      if (e != null) {
        LOG.error(
            "Response Error Details : Timestamp: {} - CorrelationId: {} - UserId: {} - ApplicationId: {} - Response Status: {} - Error Detail: {} ",
            new Date(), request.getHeader(Constants.CORRELATION_ID_STRING), getUser(request),
            dashboardId, 500, e);
      } else {
        LOG.debug(
            "Response  Details :  Timestamp: {} - CorrelationId: {} - UserId: {} - ApplicationId: {} - Response Status: {}  ",
            new Date(), request.getHeader(Constants.CORRELATION_ID_STRING), getUser(request),
            dashboardId, httpResponse.getStatus());
      }



      }
}
