package com.microservice.authentification.user.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.authentification.application.AuthController;
import com.microservice.authentification.domain.gateway.JWTAuthResponse;
import com.microservice.authentification.domain.gateway.LoginDto;
import com.microservice.authentification.domain.gateway.RegisterDto;
import com.microservice.authentification.domain.usecase.Login;
import com.microservice.authentification.domain.usecase.Register;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Objects;

import static com.microservice.authentification.domain.gateway.LoginDto.loginDtoBuilder;
import static com.microservice.authentification.domain.gateway.RegisterDto.registerDtoBuilder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private Login login;
    @Mock
    private Register register;

    @InjectMocks
    private AuthController authController;

    @Test
    void givenEmailAndPassword_whenAuthenticate_thenHaveGoodHttpStatusAndJWTToken() {
        // Arrange
        LoginDto loginDto = loginDtoBuilder().build();

        when(login.execute(loginDto)).thenReturn("dummyToken");

        // Act
        ResponseEntity<JWTAuthResponse> response = authController.authenticateUser(loginDto);

        // Assert
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals("dummyToken", Objects.requireNonNull(response.getBody()).getAccessToken()),
                () -> verify(login).execute(loginDto)
        );
    }

    @Test
    void givenUserInfos_whenRegister_thenHaveGoodHttpStatusAndCreatedMessage() throws JsonProcessingException {
        // Arrange
        RegisterDto registerDto = registerDtoBuilder().build();

        when(register.execute(registerDto)).thenReturn("User registered successfully!.");

        // Act
        ResponseEntity<String> response = authController.register(registerDto);

        // Assert
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals("User registered successfully!.", response.getBody()),
                () -> verify(register).execute(registerDto)
        );
    }

}
