package com.example.featuretest.core.persona;

import lombok.Data;

@Data
public class Persona {

    private String email;
    private String type;
    private String description;
    private String name;
    private String username;
    private String password;
    private String token;
    private boolean login;
    private boolean canLogin = true;
}
