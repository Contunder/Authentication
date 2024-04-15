package com.microservice.authentification.domain.mapper;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.gateway.UserDetailsDto;

public class UserDetailMapper {

    public UserDetailsDto mapToDto(Authentification authentification){
        return UserDetailsDto.builder()
                .email(authentification.getEmail())
                .password(authentification.getPassword())
                .roles(authentification.getRoles())
                .build();
    }
}
