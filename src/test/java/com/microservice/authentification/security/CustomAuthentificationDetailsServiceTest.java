package com.microservice.authentification.security;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.entities.Role;
import com.microservice.authentification.domain.ports.AuthentificationPorts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class CustomAuthentificationDetailsServiceTest {

    @Mock
    private AuthentificationPorts authentificationPorts;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setup() {
        customUserDetailsService = new CustomUserDetailsService(authentificationPorts);
    }

    @Test
    void testLoadUserByUsername() {
        // Arrange
        String userEmail = "test@example.com";
        Authentification authentification = new Authentification();
        authentification.setEmail(userEmail);
        authentification.setPassword("password");
        Role role = new Role();
        role.setName("ROLE_USER");
        authentification.setRoles(Collections.singleton(role));

        Mockito.when(authentificationPorts.findByEmail(userEmail)).thenReturn(Optional.of(authentification));

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

        // Assert
        Assertions.assertEquals(authentification.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(authentification.getPassword(), userDetails.getPassword());

        Set<SimpleGrantedAuthority> expectedAuthorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        Assertions.assertEquals(expectedAuthorities, userDetails.getAuthorities());

        Mockito.verify(authentificationPorts).findByEmail(userEmail);
    }

    @Test
    void testLoadUserByUsername_UsernameNotFoundException() {
        // Arrange
        String userEmail = "nonexistent@example.com";
        Mockito.when(authentificationPorts.findByEmail(userEmail)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(userEmail);
        });

        Mockito.verify(authentificationPorts).findByEmail(userEmail);
    }

}
