package org.harvanir.gradle.gradledemo.controller.advice;

import java.util.Date;
import javax.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** @author Harvan Irsyadi */
@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  private static final String ERR_OCCUR = "Error occur";

  private static final String ERR_DATA_NOT_FOUND = "Resource not found";

  private static final String ERR_CONCURRENT = "Error due to concurrent requests, please try again";

  @Builder
  @Getter
  @Setter
  private static class ErrorResponse {
    private Date timestamp;

    private int status;

    private String error;

    private String path;
  }

  private Object getBody(HttpStatus httpStatus, String errorMessage, ServletWebRequest request) {
    return ErrorResponse.builder()
        .timestamp(new Date())
        .status(httpStatus.value())
        .error(errorMessage)
        .path(WebRequestUtils.getPath(request))
        .build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundException(
      EntityNotFoundException ex, ServletWebRequest request) {
    log.error(ERR_OCCUR, ex);

    HttpStatus status = HttpStatus.NOT_FOUND;

    return handleExceptionInternal(
        ex, getBody(status, ERR_DATA_NOT_FOUND, request), new HttpHeaders(), status, request);
  }

  @ExceptionHandler({
    StaleObjectStateException.class,
    ObjectOptimisticLockingFailureException.class
  })
  public final ResponseEntity<Object> handleOptimisticLockingException(
      Exception ex, ServletWebRequest request) {
    log.error(ERR_OCCUR, ex);

    HttpStatus status = HttpStatus.CONFLICT;

    return handleExceptionInternal(
        ex, getBody(status, ERR_CONCURRENT, request), new HttpHeaders(), status, request);
  }
}
