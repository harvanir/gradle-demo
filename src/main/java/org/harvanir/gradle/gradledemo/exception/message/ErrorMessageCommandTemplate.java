package org.harvanir.gradle.gradledemo.exception.message;

import java.util.ArrayList;
import java.util.List;

/** @author Harvan Irsyadi */
public abstract class ErrorMessageCommandTemplate<T> implements ErrorMessage {

  abstract String getField(T error);

  abstract String getMessage(T error);

  abstract List<T> getErrorInternal();

  private String getMessage(String field, String message) {
    return field + ": " + message;
  }

  @Override
  public final List<String> getErrors() {
    List<String> errors = new ArrayList<>(getErrorInternal().size());

    for (T error : getErrorInternal()) {
      errors.add(getMessage(getField(error), getMessage(error)));
    }

    return errors;
  }
}
