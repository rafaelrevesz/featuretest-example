package com.example.featuretest.core.persona;

import com.example.featuretest.core.authorization.AuthorizationException;
import com.example.featuretest.core.authorization.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonaRegistry {
    private final PersonaProperty personaProperty;

    public PersonaRegistry(final PersonaProperty personaProperty,
                           @Value("${authorization.enabled}") final boolean authorizationEnabled,
                           @Value("${authorization.method}") final String authorizationMethod,
                           final LoginService loginService) {
        this.personaProperty = personaProperty;

        var personae = personaProperty.getPersonae();
        log.info("Count of available personas is {}", personae.size());

        personae.forEach((name, persona) -> {
            persona.setName(StringUtils.capitalize(name));
            log.info("Persona {} ({}) is available", persona.getName(), persona.getEmail());
            if (authorizationEnabled && persona.isLogin() && "OAUTH2".equalsIgnoreCase(authorizationMethod)) {
                try {
                    persona.setToken(loginService.login(persona.getEmail(), persona.getPassword()));
                    log.info("token: {}", persona.getToken());
                } catch (AuthorizationException e) {
                    persona.setCanLogin(false);
                    log.error("User {} cannot login: {}", persona.getName(), e.getMessage());
                }
            }
        });

        if (!authorizationEnabled) log.info("Authorization is switched off");
    }

    public Persona getPersona(String personaName) {
        var persona = personaProperty.getPersonae().get(personaName);
        if (persona == null) {
            throw new PersonaNotFoundException("Persona %s is not found".formatted(personaName));
        }
        return persona;
    }
}
