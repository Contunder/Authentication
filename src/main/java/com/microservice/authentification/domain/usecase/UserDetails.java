package com.microservice.authentification.domain.usecase;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.gateway.UserDetailsDto;
import com.microservice.authentification.domain.mapper.UserDetailMapper;
import com.microservice.authentification.domain.ports.AuthentificationPorts;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetails {

    private final AuthentificationPorts authentificationPorts;
    private final UserDetailMapper userDetailMapper;

    public UserDetails(AuthentificationPorts authentificationPorts) {
        this.authentificationPorts = authentificationPorts;
        this.userDetailMapper = new UserDetailMapper();
    }

    public UserDetailsDto execute(String email) {

        Authentification authentification = authentificationPorts.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email)
                );

        return userDetailMapper.mapToDto(authentification);
    }
}
