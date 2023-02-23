package com.example.apigateway.dtos.security;

import lombok.Data;

@Data
public class AccountCredentialsDto {

    private String username;
    private String password;

    public AccountCredentialsDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
