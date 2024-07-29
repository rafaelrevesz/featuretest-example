package com.example.featuretest.core.authorization;

import com.example.featuretest.core.persona.Persona;
import com.example.featuretest.core.persona.PersonaRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AuthorizationProvider {
    private final PersonaRegistry personaRegistry;

    @Value("${authorization.method}")
    private String authorizationMethod;

    public String authorizationForPersona(String personaName) {
        Persona persona = personaRegistry.getPersona(personaName);

        if ("OAUTH2".equalsIgnoreCase(authorizationMethod)) {
            return oauth2Authorization(persona);
        } else if ("BASIC".equalsIgnoreCase(authorizationMethod)) {
            return basicAuthorization(persona);
        } else {
            return null;
        }
    }

    private String basicAuthorization(Persona persona) {
        String credentials = persona.getUsername() + ":" + persona.getPassword();
        return new String(Base64.getEncoder().encode(credentials.getBytes()));
    }

    private String oauth2Authorization(Persona persona) {
        return "Bearer " + persona.getToken();
    }
}
