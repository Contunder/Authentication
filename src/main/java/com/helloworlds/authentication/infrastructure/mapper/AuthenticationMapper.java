package com.helloworlds.authentication.infrastructure.mapper;

import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.infrastructure.entity.Authentification;
import com.helloworlds.authentication.infrastructure.entity.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthenticationMapper {

    public Authentification map(User user, Role userRole) {
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        return Authentification.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();

    }

}
