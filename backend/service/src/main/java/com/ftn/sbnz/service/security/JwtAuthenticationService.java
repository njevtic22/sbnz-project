package com.ftn.sbnz.service.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationService implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User authenticate(String username, String password) {
        Authentication currentUserAuth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(currentUserAuth);
        return getPrincipal(currentUserAuth);
    }

    @Override
    public User getAuthenticated() {
        Authentication currentUserAuth = SecurityContextHolder.getContext().getAuthentication();
        return getPrincipal(currentUserAuth);
    }

    private User getPrincipal(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
