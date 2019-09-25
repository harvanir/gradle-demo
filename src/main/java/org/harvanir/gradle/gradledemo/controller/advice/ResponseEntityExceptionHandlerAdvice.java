package org.harvanir.gradle.gradledemo.controller.advice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

/** @author Harvan Irsyadi */
@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandlerAdvice {

  private static final String ERR_OCCUR = "Error occur";

  private static final String ERR_CONCURRENT = "Error due to concurrent requests, please try again";

  @Builder
  @Getter
  @Setter
  private static class ErrorResponse {
    private Date timestamp;

    private int status;

    private String error;

    private List<String> errors;

    private String path;
  }

  private Object getBody(HttpStatus httpStatus, String errorMessage, ServletWebRequest request) {
    return getBody(request, httpStatus, errorMessage, null);
  }

  private Object getBody(
      ServletWebRequest request, HttpStatus httpStatus, String errorMessage, List<String> errors) {
    return ErrorResponse.builder()
        .timestamp(new Date())
        .status(httpStatus.value())
        .error(errorMessage)
        .errors(errors)
        .path(WebRequestUtils.getPath(request))
        .build();
  }

  private ResponseEntity<Object> handleExceptionInternal(
      Exception e,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, RequestAttributes.SCOPE_REQUEST);
    }

    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundException(
      EntityNotFoundException e, ServletWebRequest request) {
    log.error(ERR_OCCUR, e);

    HttpStatus status = HttpStatus.NOT_FOUND;

    return handleExceptionInternal(
        e, getBody(status, status.getReasonPhrase(), request), new HttpHeaders(), status, request);
  }

  @ExceptionHandler({
    StaleObjectStateException.class,
    ObjectOptimisticLockingFailureException.class
  })
  public final ResponseEntity<Object> handleOptimisticLockingException(
      Exception e, ServletWebRequest request) {
    log.error(ERR_OCCUR, e);

    HttpStatus status = HttpStatus.CONFLICT;

    return handleExceptionInternal(
        e, getBody(status, ERR_CONCURRENT, request), new HttpHeaders(), status, request);
  }

  @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
  public final ResponseEntity<Object> handleBindException(Exception e, ServletWebRequest request) {
    log.error(ERR_OCCUR, e);

    HttpStatus status = HttpStatus.BAD_REQUEST;

    return handleExceptionInternal(
        e,
        getBody(
            request,
            status,
            status.getReasonPhrase(),
            ExceptionHandlerFactory.getProducer(e).getErrors()),
        new HttpHeaders(),
        status,
        request);
  }

  static class ExceptionHandlerFactory {
    private ExceptionHandlerFactory() {}

    static ErrorProducer getProducer(Exception e) {
      if (e instanceof MethodArgumentNotValidException) {
        return new MethodArgumentNotValidExceptionErrorProducer(e);
      }
      return new EmptyProducer();
    }
  }

  interface ErrorProducer {
    List<String> getErrors();
  }

  static class EmptyProducer implements ErrorProducer {

    @Override
    public List<String> getErrors() {
      return Collections.emptyList();
    }
  }

  static class MethodArgumentNotValidExceptionErrorProducer implements ErrorProducer {
    private final Exception exception;

    MethodArgumentNotValidExceptionErrorProducer(Exception exception) {
      this.exception = exception;
    }

    @Override
    public List<String> getErrors() {
      MethodArgumentNotValidException notValidException =
          (MethodArgumentNotValidException) exception;
      BindingResult bindingResult = notValidException.getBindingResult();
      List<String> errors = new ArrayList<>(bindingResult.getFieldErrors().size());

      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
      }

      return errors;
    }
  }
}
