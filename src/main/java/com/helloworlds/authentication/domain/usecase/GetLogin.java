package com.helloworlds.authentication.domain.usecase;

import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.domain.exception.UserException;
import com.helloworlds.authentication.domain.port.UserPort;

public class GetLogin {

    private final UserPort userPort;

    public GetLogin(UserPort userPort) {
        this.userPort = userPort;
    }

    public User execute(User user) throws UserException {
        if (userPort.validateCredential(user)) {
            return userPort.getUser(user);
        } else {
            throw new UserException("Invalid credentials");
        }
    }

}
