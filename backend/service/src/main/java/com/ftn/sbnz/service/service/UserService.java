package com.ftn.sbnz.service.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, List<String>> getTakenEmailsAndUsernames();


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // getByUsername
    // getProfile
    // changePassword
}
