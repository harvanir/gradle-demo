package org.harvanir.gradle.gradledemo.exception.message;

import java.util.List;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/** @author Harvan Irsyadi */
class MethodArgumentNotValidExceptionCommand extends ErrorMessageCommandTemplate<FieldError>
    implements ErrorMessage {
  private final MethodArgumentNotValidException exception;

  MethodArgumentNotValidExceptionCommand(MethodArgumentNotValidException exception) {
    this.exception = exception;
  }

  @Override
  String getField(FieldError error) {
    return error.getField();
  }

  @Override
  String getMessage(FieldError error) {
    return error.getDefaultMessage();
  }

  @Override
  List<FieldError> getErrorInternal() {
    return exception.getBindingResult().getFieldErrors();
  }
}
