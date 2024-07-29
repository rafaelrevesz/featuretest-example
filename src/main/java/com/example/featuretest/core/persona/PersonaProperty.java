package com.example.featuretest.core.persona;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "example.api-service")
class PersonaProperty {
    @Getter
    private Map<String, Persona> personae = new HashMap<>();
}
