package com.example.featuretest.api;

import com.example.featuretest.core.RunContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@RequiredArgsConstructor
public class ApiSteps {
    private final ApiManagementApiClient apiManagementApiClient;
    private final RunContext runContext;

    @When("^([^ ]+) calls the API service health endpoint$")
    public void readHealthEndpoint(String personaName) {
        runContext.setHttpStatus(apiManagementApiClient.callHealth().getStatusCode());
    }

    @Then("^([^ ]+) receives HTTP (\\d+)$")
    public void verifyHealthEndpointStatusCode(String personaName, int statusCode) {
        assertThat(runContext.getHttpStatus().value(), is(statusCode));
    }
}
