package org.harvanir.gradle.gradledemo.configuration;

import org.harvanir.gradle.gradledemo.configuration.properties.TestProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(TestProperties.class)
@Configuration
public class TestConfiguration {}
