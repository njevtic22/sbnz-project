package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.admin.AddAdminDto;
import com.ftn.sbnz.service.dto.admin.AdminViewDto;
import com.ftn.sbnz.service.mapper.AdminMapper;
import com.ftn.sbnz.service.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService service;
    private final AdminMapper mapper;

    public AdminController(AdminService service, AdminMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> addAdmin(@Valid @RequestBody AddAdminDto newAdminDto, UriComponentsBuilder uriBuilder) {
        Admin newAdmin = mapper.toModel(newAdminDto);
        Admin added = service.add(newAdmin, newAdminDto.getRepeatedPassword());

        URI uri = uriBuilder
                .path("/api/admins/{id}")
                .buildAndExpand(added.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<AdminViewDto>> getAdmins(Pageable pageable) {
        Page<Admin> allAdmins = service.getAll(pageable);
        Page<AdminViewDto> allAdminsDto = allAdmins.map(mapper::toViewDto);

        PaginatedResponse<AdminViewDto> responseDto = new PaginatedResponse<>(
                allAdminsDto.getContent(),
                allAdminsDto.getTotalElements(),
                allAdminsDto.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }
}
