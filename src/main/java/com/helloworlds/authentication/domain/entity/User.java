package com.helloworlds.authentication.domain.entity;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    @Setter
    private String token;
    @Setter
    private String role;

    public String toJson(){
        return new Gson().toJson(this);
    }
}

