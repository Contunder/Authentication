package com.microservice.authentification.domain.mapper;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.entities.Role;
import com.microservice.authentification.domain.gateway.RegisterDto;

import java.util.HashSet;
import java.util.Set;

public class AuthentificationMapper {

    public Authentification mapToModel(RegisterDto registerDto, Role userRole) {
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        return Authentification.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .roles(roles)
                .build();

    }

}
