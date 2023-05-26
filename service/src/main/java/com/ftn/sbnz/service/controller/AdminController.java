package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.admin.AddAdminDto;
import com.ftn.sbnz.service.dto.admin.AdminViewDto;
import com.ftn.sbnz.service.dto.admin.UpdateAdminDto;
import com.ftn.sbnz.service.dto.admin.UpdateAdminResponseDto;
import com.ftn.sbnz.service.mapper.AdminMapper;
import com.ftn.sbnz.service.security.TokenService;
import com.ftn.sbnz.service.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final TokenService tokenService;

    public AdminController(AdminService service, AdminMapper mapper, TokenService tokenService) {
        this.service = service;
        this.mapper = mapper;
        this.tokenService = tokenService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminViewDto> getAdmin(@PathVariable Long id) {
        Admin found = service.getById(id);
        AdminViewDto foundDto = mapper.toViewDto(found);
        return ResponseEntity.ok(foundDto);
    }

    // TODO: profile

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateAdminResponseDto> updateAdmin(@PathVariable Long id, @Valid @RequestBody UpdateAdminDto changesDto) {
        Admin original = service.getById(id);
        String originalUsername = original.getUsername();

        Admin changes = mapper.toModel(changesDto);
        Admin updated = service.update(id, changes);

        String jwt = "";
        if (!originalUsername.equals(updated.getUsername())) {
            jwt = tokenService.getToken(updated.getUsername());
        }

        AdminViewDto updatedDto = mapper.toViewDto(updated);
        return ResponseEntity.ok(new UpdateAdminResponseDto(updatedDto, jwt));
    }

    // TODO: password

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
