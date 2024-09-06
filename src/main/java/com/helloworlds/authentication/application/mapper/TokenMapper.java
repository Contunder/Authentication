package com.helloworlds.authentication.application.mapper;

import com.helloworlds.authentication.application.entity.JWTAuthResponse;
import com.helloworlds.authentication.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    private static final String TOKEN_TYPE = "Bearer";

    public JWTAuthResponse map(User user) {
        return new JWTAuthResponse(user.getToken(), TOKEN_TYPE);
    }

}
