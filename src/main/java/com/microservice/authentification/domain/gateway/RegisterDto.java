package com.microservice.authentification.domain.gateway;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
public class RegisterDto {
    private String name;
    private String lastName;
    private Date birthday;
    private String address;
    private String zipCode;
    private String city;
    private String email;
    private String password;

    public static RegisterDtoBuilder registerDtoBuilder(){
        return RegisterDto.builder();
    }
}
