package com.microservice.authentification.security;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.ports.AuthentificationPorts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthentificationPorts authentificationPorts;

    public CustomUserDetailsService(AuthentificationPorts authentificationPorts) {
        this.authentificationPorts = authentificationPorts;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
          Authentification authentification = authentificationPorts.findByEmail(userEmail)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with email: " + userEmail));

        Set<GrantedAuthority> authorities = authentification
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(authentification.getEmail(),
                authentification.getPassword(),
                authorities);
    }
}
