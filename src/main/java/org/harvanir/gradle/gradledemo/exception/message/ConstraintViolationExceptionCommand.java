package org.harvanir.gradle.gradledemo.exception.message;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/** @author Harvan Irsyadi */
class ConstraintViolationExceptionCommand
    extends ErrorMessageCommandTemplate<ConstraintViolation<?>> implements ErrorMessage {

  private final List<ConstraintViolation<?>> errors;

  ConstraintViolationExceptionCommand(ConstraintViolationException exception) {
    this.errors = new ArrayList<>(exception.getConstraintViolations());
  }

  @Override
  String getField(ConstraintViolation<?> error) {
    return error.getPropertyPath().toString();
  }

  @Override
  String getMessage(ConstraintViolation<?> error) {
    return error.getMessage();
  }

  @Override
  List<ConstraintViolation<?>> getErrorInternal() {
    return errors;
  }
}
