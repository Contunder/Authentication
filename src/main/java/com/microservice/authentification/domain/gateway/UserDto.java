package com.microservice.authentification.domain.gateway;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private String lastName;
    private String birthday;
    private String address;
    private String zipCode;
    private String city;
    private String email;

    public String toJson(){

        return new Gson().toJson(this);
    }
}
