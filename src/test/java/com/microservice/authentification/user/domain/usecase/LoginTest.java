package com.microservice.authentification.user.domain.usecase;

import com.microservice.authentification.domain.usecase.Login;
import com.microservice.authentification.security.JwtTokenProvider;
import com.microservice.authentification.domain.gateway.LoginDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static com.microservice.authentification.domain.gateway.LoginDto.loginDtoBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private Login login;

    @Test
    void shouldHaveJWTToken_whenHaveEmailAndPassword() {
        // Arrange
        LoginDto loginDto = loginDtoBuilder()
                .email("test@example.com")
                .password("password")
                .build();

        String expectedToken = "test_token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn(expectedToken);

        // Act
        String token = login.execute(loginDto);

        // Assert
        assertEquals(expectedToken, token);
    }

}