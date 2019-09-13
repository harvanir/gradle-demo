package org.harvanir.gradle.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class GradleDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GradleDemoApplication.class, args);
  }
}
