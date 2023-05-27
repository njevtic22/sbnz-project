package com.ftn.sbnz.service.dto.auth;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "Username must not be blank.")
    private String username;

    //    @Password
    @NotBlank(message = "Password must not be blank.")
    private String password;

    public LoginDto() { }

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
