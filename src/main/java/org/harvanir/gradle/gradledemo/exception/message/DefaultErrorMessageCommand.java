package org.harvanir.gradle.gradledemo.exception.message;

import java.util.Collections;
import java.util.List;

/** @author Harvan Irsyadi */
public class DefaultErrorMessageCommand implements ErrorMessage {

  static final DefaultErrorMessageCommand INSTANCE = new DefaultErrorMessageCommand();

  private DefaultErrorMessageCommand() {}

  @Override
  public List<String> getErrors() {
    return Collections.emptyList();
  }
}
