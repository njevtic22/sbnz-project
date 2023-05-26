package com.ftn.sbnz.service.dto.auth;

public class TokenDto {
    private final String token;
    private final String role;

    public TokenDto(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}
