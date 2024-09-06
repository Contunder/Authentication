package com.helloworlds.authentication.infrastructure.dao;

import com.helloworlds.authentication.domain.entity.User;
import com.helloworlds.authentication.infrastructure.mapper.JsonBodyHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Component
public class UserDAO {

    @Value("${user.create_user}")
    private String createUserUrl;
    @Value("${user.api_version}")
    private String version;
    private final HttpClient httpClient;

    public UserDAO() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public void createUser(User user) {
        HttpRequest createUserRequest = HttpRequest.newBuilder(
                        URI.create(createUserUrl)
                )
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("x-api-version", version)
                .POST(HttpRequest.BodyPublishers.ofString(user.toJson()))
                .build();

        httpClient.sendAsync(createUserRequest, new JsonBodyHandler<>(User.class));
    }
}
