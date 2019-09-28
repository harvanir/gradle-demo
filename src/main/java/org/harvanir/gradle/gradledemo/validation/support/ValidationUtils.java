package org.harvanir.gradle.gradledemo.validation.support;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/** @author Harvan Irsyadi */
public class ValidationUtils {
  private ValidationUtils() {}

  public static void validate(Validator validator, Object bean) {
    Set<ConstraintViolation<Object>> validate = validator.validate(bean);

    if (!validate.isEmpty()) {
      throw new ConstraintViolationException(validate);
    }
  }
}
