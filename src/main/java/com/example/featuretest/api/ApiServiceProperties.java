package com.example.featuretest.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "example.api-service")
public class ApiServiceProperties {

    @Value("${url}")
    private String url;

    public String toApplicationAdminUrl(String path) {
        return url + path;
    }

    public String applicationApiUrlPrefix() {
        return url + "/api/v1";
    }
}
