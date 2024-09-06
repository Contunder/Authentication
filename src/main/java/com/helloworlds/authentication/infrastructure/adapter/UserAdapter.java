package com.helloworlds.authentication.infrastructure.adapter;

import com.helloworlds.authentication.TokenProvider;
import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.domain.exception.UserException;
import com.helloworlds.authentication.domain.port.UserPort;
import com.helloworlds.authentication.infrastructure.dao.AuthenticationDAO;
import com.helloworlds.authentication.infrastructure.dao.RoleDAO;
import com.helloworlds.authentication.infrastructure.dao.UserDAO;
import com.helloworlds.authentication.infrastructure.entity.Role;
import com.helloworlds.authentication.infrastructure.exception.SqlException;
import com.helloworlds.authentication.infrastructure.mapper.AuthenticationMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserPort {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final AuthenticationDAO authenticationDAO;
    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final AuthenticationMapper authenticationMapper;

    public UserAdapter(AuthenticationManager authenticationManager, TokenProvider tokenProvider, AuthenticationDAO authenticationDAO, RoleDAO roleDAO, UserDAO userDAO, AuthenticationMapper authenticationMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.authenticationDAO = authenticationDAO;
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.authenticationMapper = authenticationMapper;
    }

    @Override
    public User getUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        user.setToken(tokenProvider.generateToken(authentication));

        return user;
    }

    @Override
    public User createUser(User user) throws UserException {
        try {
            if (authenticationDAO.existsByEmail(user.getEmail())) {
                throw new UserException("Email is already exists!.");
            } else {
                userDAO.createUser(user);
                authenticationDAO.save(authenticationMapper.map(user, getRole(user.getRole())));

                return user;
            }
        } catch (SqlException sqlException) {
            throw new UserException(sqlException.getMessage());
        }
    }

    @Override
    public boolean validateCredential(User user) {
        return authenticationDAO.existsByEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        );
    }

    public Role getRole(String role) {
        return roleDAO.findByName(role)
                .orElseThrow(
                        () -> new SqlException("Role Not Found")
                );
    }
}
