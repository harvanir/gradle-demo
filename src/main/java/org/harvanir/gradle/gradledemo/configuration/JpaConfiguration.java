package org.harvanir.gradle.gradledemo.configuration;

import org.harvanir.gradle.gradledemo.repository.support.QueryDslJpaEnhancedRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** @author Harvan Irsyadi */
@EnableJpaRepositories(
    repositoryBaseClass = QueryDslJpaEnhancedRepositoryImpl.class,
    basePackages = "org.harvanir.gradle.gradledemo.repository")
@EnableJpaAuditing
@Configuration
public class JpaConfiguration {}
