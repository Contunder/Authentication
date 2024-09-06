package com.helloworlds.authentication.domain.port;

import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.domain.exception.UserException;

public interface UserPort {
    User getUser(User user);

    User createUser(User user) throws UserException;

    boolean validateCredential(User user);
}
