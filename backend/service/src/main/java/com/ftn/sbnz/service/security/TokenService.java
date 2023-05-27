package com.ftn.sbnz.service.security;

public interface TokenService {
    String getToken(String username);
    long getExpiresIn();
}
