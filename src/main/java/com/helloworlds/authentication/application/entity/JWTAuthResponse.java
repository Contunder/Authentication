package com.helloworlds.authentication.application.entity;

public record JWTAuthResponse (String accessToken, String tokenType) { }
