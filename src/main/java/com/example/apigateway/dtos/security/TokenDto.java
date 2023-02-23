package com.example.apigateway.dtos.security;

import lombok.Data;

import java.util.Date;

@Data
public class TokenDto {

    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expirated;
    private String accessToken;
    private String refreshToken;

    public TokenDto(String username, Boolean authenticated, Date created, Date expirated, String accessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expirated = expirated;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
