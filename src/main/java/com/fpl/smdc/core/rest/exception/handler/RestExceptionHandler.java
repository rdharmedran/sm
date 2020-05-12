
package com.fpl.smdc.core.rest.exception.handler;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fpl.smdc.core.log.LoggingService;
import com.fpl.smdc.core.model.ErrorDetail;
import com.fpl.smdc.core.rest.exception.response.ErrorResponse;
import com.fpl.smdc.util.Constants;

/**
 * The Class RestExceptionHandler.
 */
@ControllerAdvice
@RestController
@Order(1)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /** The Constant ERROR_105. */
  private static final int ERROR_105 = 105;

  /** The cache manager. */
  @Autowired
  CacheManager cacheManager;

  /** The logging service. */
  @Autowired
  LoggingService loggingService;

  /**
   * Handle user not found exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public final ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex,
      HttpServletRequest request) {

    loggingService.logError(request, ex);

    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = null;
    if (cacheManager == null
        || (cacheManager.getCache("errors").get(ex.getClass().getSimpleName()) == null)) {
      error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), ERROR_105,
          ex.getClass().getSimpleName(), details, request.getRequestURI(),
          request.getHeader(Constants.CORRELATION_ID_STRING));

    } else {
      ErrorDetail detail =
          (ErrorDetail) cacheManager.getCache("errors").get(ex.getClass().getSimpleName()).get();
      if (detail != null) {
        error = new ErrorResponse(detail.getStatusType(), detail.getErrorCode(),
            detail.getErrorMsg(), details, request.getRequestURI(),
            request.getHeader(Constants.CORRELATION_ID_STRING));
      }
    }

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
