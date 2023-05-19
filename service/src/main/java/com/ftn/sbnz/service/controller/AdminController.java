package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.service.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins(Pageable pageable) {
        Page<Admin> allAdmins = service.getAll(pageable);
        return ResponseEntity.ok(allAdmins.toList());
    }
}
