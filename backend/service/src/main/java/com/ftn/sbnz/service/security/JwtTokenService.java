package com.ftn.sbnz.service.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenService implements TokenService {
    private final TokenUtils tokenUtils;

    public JwtTokenService(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public String getToken(String username) {
        return tokenUtils.generateToken(username);
    }

    @Override
    public long getExpiresIn() {
        return tokenUtils.getExpiredIn();
    }
}
