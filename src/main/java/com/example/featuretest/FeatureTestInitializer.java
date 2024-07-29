package com.example.featuretest;

import com.example.featuretest.core.Clearable;
import com.example.featuretest.core.persona.PersonaRegistry;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

@Slf4j
@ContextConfiguration
@RequiredArgsConstructor
public class FeatureTestInitializer {
    private final Set<Clearable> clearables;

    private final PersonaRegistry personaRegistry;

    @Before
    public void initialize() {
        resetClearables();
    }

    private void resetClearables() {
        clearables.forEach(Clearable::clear);
    }
}
