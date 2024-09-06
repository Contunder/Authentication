package com.helloworlds.authentication.application.mapper;

import com.helloworlds.authentication.application.entity.Login;
import com.helloworlds.authentication.application.entity.Register;
import com.helloworlds.authentication.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(Login login) {
        return User.builder()
                .email(login.email())
                .password(login.password())
                .build();
    }

    public User map(Register register) {
        return User.builder()
                .name(register.name())
                .lastName(register.lastName())
                .email(register.email())
                .password(register.password())
                .build();
    }

}
