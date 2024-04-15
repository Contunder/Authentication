package com.microservice.authentification.domain.mapper;

import com.microservice.authentification.domain.gateway.RegisterDto;
import com.microservice.authentification.domain.gateway.UserDto;

public class UserMapper {

    public UserDto mapToDTO(RegisterDto registerDto){
        return UserDto.builder()
                .name(registerDto.getName())
                .lastName(registerDto.getLastName())
                .birthday(registerDto.getBirthday().toString())
                .email(registerDto.getEmail())
                .address(registerDto.getAddress())
                .zipCode(registerDto.getZipCode())
                .city(registerDto.getCity())
                .build();
    }

}
