package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.security.TokenService;
import com.ftn.sbnz.service.util.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    public LoginServiceImpl(TokenService tokenService, AuthenticationService authenticationService) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    public Pair<String, String> login(String username, String password) {
        User authentication = authenticationService.authenticate(username, password);
        String token = tokenService.getToken(authentication.getUsername());

        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .get(0);

        return new Pair<>(token, role);
    }
}
