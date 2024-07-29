package com.example.featuretest.core.messaging.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CustomHttpHeadersBuilder {
    private MediaType contentType;
    private String accessToken;
    private String jSessionId;

    public CustomHttpHeadersBuilder contentType(MediaType contentType) {
        this.contentType = contentType;
        return this;
    }

    public CustomHttpHeadersBuilder authorization(String authorization) {
        this.accessToken = authorization;
        return this;
    }

    public CustomHttpHeadersBuilder jSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
        return this;
    }

    public HttpHeaders build() {
        var requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(contentType);
        requestHeaders.set(HttpHeaders.COOKIE, "JSESSIONID=" + jSessionId);
        if (accessToken != null) {
            requestHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
        }

        return requestHeaders;
    }
}
