package com.example.featuretest;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan
@SpringBootTest(classes = FeatureTestConfig.class)
@EnableConfigurationProperties
@CucumberContextConfiguration
public class FeatureTestConfig {
}
