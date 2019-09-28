package org.harvanir.gradle.gradledemo.exception.message;

import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

/** @author Harvan Irsyadi */
public class ErrorMessageFactory {
  private ErrorMessageFactory() {}

  public static ErrorMessage getCommand(Exception e) {
    if (e instanceof MethodArgumentNotValidException) {
      return new MethodArgumentNotValidExceptionCommand((MethodArgumentNotValidException) e);
    } else if (e instanceof ConstraintViolationException) {
      return new ConstraintViolationExceptionCommand((ConstraintViolationException) e);
    }

    return DefaultErrorMessageCommand.INSTANCE;
  }
}
