package com.example.featuretest.api;

import com.example.featuretest.core.messaging.http.CustomHttpHeadersBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiManagementApiClient {

    private final ApiServiceProperties apiServiceProperties;
    private final RestTemplate restTemplate;

    public ResponseEntity<Void> callHealth() {
        String url = apiServiceProperties.toApplicationAdminUrl("/actuator/health");

        var requestHeaders = new CustomHttpHeadersBuilder()
                .contentType(MediaType.APPLICATION_JSON)
                .build();

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(requestHeaders), Void.class);
    }
}
