package com.microservice.authentification.security;

import com.microservice.authentification.exception.AuthentificationAPIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class JwtTokenProviderTest {

    @Test
    void testValidateToken_ExpiredToken() {
        // Arrange
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = "expired_token";

        // Act and Throws
        Assertions.assertThrows(AuthentificationAPIException.class, () -> jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_MalformedToken() {
        // Arrange
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = "malformed_token";

        // Act and Throws
        Assertions.assertThrows(AuthentificationAPIException.class, () -> jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_UnsupportedToken() {
        // Arrange
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = "unsupported_token";

        // Act and Throws
        Assertions.assertThrows(AuthentificationAPIException.class, () -> jwtTokenProvider.validateToken(token));
    }

    @Test
    void testValidateToken_EmptyClaimsString() {
        // Arrange
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = "empty_claims_token";

        // Act and Throws
        Assertions.assertThrows(AuthentificationAPIException.class, () -> jwtTokenProvider.validateToken(token));
    }

}
