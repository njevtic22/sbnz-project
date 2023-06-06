package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/taken-emails-usernames")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, List<String>>> getTakenEmailsAndUsernames() {
        Map<String, List<String>> taken = userService.getTakenEmailsAndUsernames();
        return ResponseEntity.ok(taken);
    }
}
