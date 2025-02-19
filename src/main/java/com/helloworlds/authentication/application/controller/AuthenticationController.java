package com.helloworlds.authentication.application.controller;

import com.helloworlds.authentication.application.entity.Login;
import com.helloworlds.authentication.application.entity.Register;
import com.helloworlds.authentication.application.mapper.UserMapper;
import com.helloworlds.authentication.application.presenter.Presenter;
import com.helloworlds.authentication.domain.exception.UserException;
import com.helloworlds.authentication.domain.usecase.GetLogin;
import com.helloworlds.authentication.domain.usecase.GetRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    public static final String USER_CREATED = "User Successfully Created";

    private final Presenter presenter;
    private final GetLogin getLogin;
    private final GetRegister getRegister;
    private final UserMapper userMapper;

    public AuthenticationController(Presenter presenter, GetLogin getLogin, GetRegister getRegister, UserMapper userMapper) {
        this.presenter = presenter;
        this.getLogin = getLogin;
        this.getRegister = getRegister;
        this.userMapper = userMapper;
    }

    @PostMapping(value = {"/login", "/signing"}, headers = "x-api-version=1")
    public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
        try {

            return presenter.presentSuccess(
                    getLogin.execute(userMapper.map(login))
            );
        } catch (UserException userException) {

            return presenter.presentFailure(userException);
        }
    }

    @PostMapping(value = {"/register", "/signup"}, headers = "x-api-version=1")
    public ResponseEntity<?> registerUser(@RequestBody Register register) {
        try {
            getRegister.execute(userMapper.map(register));

            return presenter.presentSuccess(USER_CREATED);
        } catch (UserException userException) {

            return presenter.presentFailure(userException);
        }
    }

}