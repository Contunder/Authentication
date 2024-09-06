package com.helloworlds.authentication.application.presenter;

import com.helloworlds.authentication.application.entity.JWTAuthResponse;
import com.helloworlds.authentication.application.exception.AuthenticationAPIException;
import com.helloworlds.authentication.application.mapper.TokenMapper;
import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.domain.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Presenter {

    private final TokenMapper tokenMapper;

    public Presenter(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    public ResponseEntity<JWTAuthResponse> presentSuccess(User user) {
        return new ResponseEntity<>(tokenMapper.map(user), HttpStatus.OK);
    }

    public ResponseEntity<String> presentSuccess(String successResponse) {
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    public ResponseEntity<AuthenticationAPIException> presentFailure(UserException userException) {
        return new ResponseEntity<>(
                new AuthenticationAPIException(HttpStatus.BAD_REQUEST, userException.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}
