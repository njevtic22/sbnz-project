package com.ftn.sbnz.service.service;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // getByUsername
    // getProfile
    // changePassword
}
