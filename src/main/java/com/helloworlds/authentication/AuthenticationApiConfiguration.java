package com.helloworlds.authentication;

import com.helloworlds.authentication.domain.port.UserPort;
import com.helloworlds.authentication.domain.usecase.GetLogin;
import com.helloworlds.authentication.domain.usecase.GetRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationApiConfiguration {

    private final UserPort userPort;

    public AuthenticationApiConfiguration(UserPort userPort) {
        this.userPort = userPort;
    }

    @Bean
    public GetLogin getLogin() {
        return new GetLogin(userPort);
    }

    @Bean
    public GetRegister getRegister() {
        return new GetRegister(userPort);
    }
}
