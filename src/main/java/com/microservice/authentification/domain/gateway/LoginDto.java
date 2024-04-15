package com.microservice.authentification.domain.gateway;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {
    private String email;
    private String password;

    public static LoginDtoBuilder loginDtoBuilder(){
        return LoginDto.builder();
    }
}
