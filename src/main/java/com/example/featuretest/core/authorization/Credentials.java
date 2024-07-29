package com.example.featuretest.core.authorization;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Credentials {

    private String username;
    private String password;
}
