package com.ftn.sbnz.service.security.rest;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {
    private final String token;
    private final UserDetails principal;

    public TokenBasedAuthentication(String token, UserDetails principal) {
        super(principal.getAuthorities());
        this.token = token;
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        // At this point token should be valid, so the user is authenticated
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
