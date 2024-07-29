package com.example.featuretest.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] requestBody, ClientHttpRequestExecution ex) throws IOException {
        log.debug("Request body: {}", new String(requestBody, StandardCharsets.UTF_8));

        var response = ex.execute(httpRequest, requestBody);

        //https://www.baeldung.com/spring-resttemplate-logging#3-resttemplate-interceptor-drawback
        if (log.isDebugEnabled()) {
            var responseInputStreamReader = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            var responseBody = new BufferedReader(responseInputStreamReader).lines()
                    .collect(Collectors.joining("\n"));

            log.debug("Response responseBody: {}", responseBody);
        }
        return response;
    }
}