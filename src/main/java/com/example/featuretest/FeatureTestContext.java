package com.example.featuretest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

@Slf4j
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners
@RequiredArgsConstructor
public class FeatureTestContext {

    private long startedAt;

    @Before
    public void logScenario(final Scenario scenario) {
        startedAt = System.nanoTime();
        log.info("Scenario: {}", scenario.getName());
    }

    @After
    public void logDuration() {
        final long stoppedAt = System.nanoTime();
        log.info("Scenario took {}", stoppedAt - startedAt);
    }
}
