package org.harvanir.gradle.gradledemo.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "test.configuration")
public class TestProperties {

  private String value;

  private Retry retry = new Retry();

  @Getter
  @Setter
  static class Retry {
    long maxAttempts = 1000;

    long delay = 1;
  }
}
