package org.harvanir.gradle.gradledemo.configuration;

import org.harvanir.gradle.gradledemo.configuration.properties.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@EnableConfigurationProperties(AppProperties.class)
@Configuration
public class AppConfiguration {}
