package org.harvanir.gradle.gradledemo.controller.advice;

import org.springframework.web.context.request.ServletWebRequest;

/** @author Harvan Irsyadi */
class WebRequestUtils {

  private WebRequestUtils() {}

  static String getPath(ServletWebRequest request) {
    return request.getRequest().getRequestURI();
  }
}
