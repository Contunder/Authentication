package com.helloworlds.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.helloworlds.authentication")
@EnableJpaRepositories("com.helloworlds.authentication")
public class AuthenticationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApiApplication.class, args);
    }

}
