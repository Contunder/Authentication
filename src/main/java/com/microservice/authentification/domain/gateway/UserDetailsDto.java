package com.microservice.authentification.domain.gateway;

import com.microservice.authentification.domain.entities.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDetailsDto {
    String email;
    String password;
    Set<Role> roles;
}
