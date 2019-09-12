package org.harvanir.gradle.gradledemo.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.configuration")
public class AppProperties {

  private Retry retry = new Retry();

  @Getter
  @Setter
  static class Retry {
    long maxAttempts = 1000;

    long delay = 1;
  }
}
