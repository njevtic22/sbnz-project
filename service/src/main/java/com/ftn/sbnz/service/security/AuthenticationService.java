package com.ftn.sbnz.service.security;

import org.springframework.security.core.userdetails.User;

public interface AuthenticationService {
    User authenticate(String username, String password);
    User getAuthenticated();
}
