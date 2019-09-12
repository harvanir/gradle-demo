package org.harvanir.gradle.gradledemo;

import org.harvanir.gradle.gradledemo.repository.support.QueryDslJpaEnhancedRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableJpaRepositories(
    repositoryBaseClass = QueryDslJpaEnhancedRepositoryImpl.class,
    basePackages = "org.harvanir.gradle.gradledemo.repository")
@EnableJpaAuditing
@SpringBootApplication
public class GradleDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(GradleDemoApplication.class, args);
  }
}
