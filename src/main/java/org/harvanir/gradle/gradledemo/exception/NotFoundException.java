package org.harvanir.gradle.gradledemo.exception;

/** @author Harvan Irsyadi */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
