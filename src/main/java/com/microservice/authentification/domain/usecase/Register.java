package com.microservice.authentification.domain.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.microservice.authentification.domain.entities.Role;
import com.microservice.authentification.domain.gateway.RegisterDto;
import com.microservice.authentification.domain.gateway.UserDto;
import com.microservice.authentification.domain.mapper.AuthentificationMapper;
import com.microservice.authentification.domain.mapper.UserMapper;
import com.microservice.authentification.domain.ports.AuthentificationPorts;
import com.microservice.authentification.domain.ports.RolePorts;
import com.microservice.authentification.exception.AuthentificationAPIException;
import com.microservice.authentification.utils.JsonBodyHandler;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Component
public class Register {

    @Value("${user.create_user}")
    private String createUserUrl;

    private final AuthentificationPorts authentificationPorts;
    private final RolePorts rolePorts;
    private final PasswordEncoder passwordEncoder;
    private final AuthentificationMapper authentificationMapper;
    private final UserMapper userMapper;
    private final HttpClient httpClient;


    public Register(AuthentificationPorts authentificationPorts, RolePorts rolePorts, PasswordEncoder passwordEncoder) {
        this.authentificationPorts = authentificationPorts;
        this.rolePorts = rolePorts;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = new UserMapper();
        this.authentificationMapper = new AuthentificationMapper();
        this.httpClient = HttpClient.newHttpClient();
    }


    public String execute(RegisterDto registerDto) {

        if (authentificationPorts.existsByEmail(registerDto.getEmail())) {
            throw new AuthentificationAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        createUserInUserAPI(registerDto);

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        authentificationPorts.save(authentificationMapper.mapToModel(registerDto, getOrCreateRole()));


        return "User registered successfully!.";
    }

    private Role getOrCreateRole() {
        if(!rolePorts.existsByName("ROLE_USER")){
            rolePorts.save(Role.builder().name("ROLE_USER").build());
        }

        return rolePorts.findByName("ROLE_USER")
                .orElseThrow(
                        () -> new AuthentificationAPIException(HttpStatus.NOT_FOUND, "Role Not Found")
                );
    }

    private void createUserInUserAPI(RegisterDto registerDto) {
        HttpRequest createUserRequest = HttpRequest.newBuilder(
                        URI.create(createUserUrl)
                )
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userMapper.mapToDTO(registerDto).toJson()))
                .build();

        httpClient.sendAsync(createUserRequest, new JsonBodyHandler<>(UserDto.class));
    }

}
