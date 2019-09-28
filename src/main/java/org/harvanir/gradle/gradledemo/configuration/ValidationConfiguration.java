package org.harvanir.gradle.gradledemo.configuration;

import javax.validation.Validator;
import org.harvanir.gradle.gradledemo.validation.support.ValidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@Configuration
public class ValidationConfiguration {

  @Bean
  public ValidationAspect validationAspect(Validator validator) {
    return new ValidationAspect(validator);
  }
}
