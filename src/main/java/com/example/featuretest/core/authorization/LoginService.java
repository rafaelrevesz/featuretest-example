package com.example.featuretest.core.authorization;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${authorization.oauth2.access-token-endpoint}")
    private String accessTokenUrl;
    @Value("${authorization.oauth2.client}")
    private String authorizationClient;

    private final RestTemplate authorizationRestTemplate;

    public String login(String email, String password) {
        log.info("Login with {}", email);
        return getAccessToken(email, password);
    }

    private String getAccessToken(String email, String password) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", authorizationClient);
        map.add("username", email);
        map.add("password", password);
        map.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity =
                authorizationRestTemplate.exchange(accessTokenUrl,
                        HttpMethod.POST,
                        entity,
                        String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                log.info("Parse token from body: {}", responseEntity.getBody());
                JsonPath.parse(responseEntity.getBody()).read("$.access_token");
                String accessToken = JsonPath.parse(responseEntity.getBody()).read("$.access_token");
                log.debug("Access token: {}", accessToken);
                return accessToken;
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot get access token", e);
            }
        } else {
            log.error("Failed to get access token (HTTP {}): {}", responseEntity.getStatusCode().value(), responseEntity.getBody());
        }
        throw new IllegalArgumentException("User " + email + " has no access to application");
    }
}
