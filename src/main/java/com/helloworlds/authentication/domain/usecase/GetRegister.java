package com.helloworlds.authentication.domain.usecase;

import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.domain.exception.UserException;
import com.helloworlds.authentication.domain.port.UserPort;

public class GetRegister {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserPort userPort;

    public GetRegister(UserPort userPort) {
        this.userPort = userPort;
    }


    public void execute(User user) throws UserException {
        user.setRole(ROLE_USER);
        userPort.createUser(user);
    }

}
