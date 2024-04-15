package com.microservice.authentification.user.domain.usecase;

import com.microservice.authentification.domain.entities.Authentification;
import com.microservice.authentification.domain.entities.Role;
import com.microservice.authentification.domain.gateway.RegisterDto;
import com.microservice.authentification.domain.ports.AuthentificationPorts;
import com.microservice.authentification.domain.ports.RolePorts;
import com.microservice.authentification.domain.usecase.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.http.HttpClient;
import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.microservice.authentification.domain.entities.Authentification.authentificationBuilder;
import static com.microservice.authentification.domain.gateway.RegisterDto.registerDtoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterTest {

    @Mock
    private AuthentificationPorts authentificationPorts;
    @Mock
    private RolePorts rolePorts;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private HttpClient httpClient;

    private Register register;

    @BeforeEach
    void setup() {
        register = new Register(authentificationPorts, rolePorts, passwordEncoder);
    }

    @Test
    void shouldCreateUser_whenUserInfosIsComplete() {
        // Arrange
//        RegisterDto registerDto = registerDtoBuilder()
//                .email("john.doe@example.com")
//                .name("John")
//                .lastName("Doe")
//                .password("password")
//                .birthday(new Date(2023,7,6))
//                .zipCode("12345")
//                .city("City")
//                .address("Adress")
//                .build();
//
//        Role userRole = new Role();
//        userRole.setId(1L);
//        userRole.setName("ROLE_USER");
//
//        Authentification savedUser = authentificationBuilder()
//                .id(1L)
//                .build();
//
//        when(authentificationPorts.existsByEmail(registerDto.getEmail())).thenReturn(false);
//        when(rolePorts.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
//        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encoded_password");
//        when(authentificationPorts.save(Mockito.any(Authentification.class))).thenReturn(savedUser);
//        when(httpClient.sendAsync(any(), any())).thenReturn(new CompletableFuture<>());

        // Act
//        String result = register.execute(registerDto);

        // Assert
//        assertEquals("User registered successfully!.", result);
    }

}