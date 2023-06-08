package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.teacher.AddTeacherDto;
import com.ftn.sbnz.service.dto.teacher.TeacherViewDto;
import com.ftn.sbnz.service.dto.teacher.UpdateTeacherDto;
import com.ftn.sbnz.service.dto.teacher.UpdateTeacherResponseDto;
import com.ftn.sbnz.service.dto.user.PasswordChangeDto;
import com.ftn.sbnz.service.mapper.TeacherMapper;
import com.ftn.sbnz.service.security.TokenService;
import com.ftn.sbnz.service.service.TeacherService;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;
    private final TeacherMapper mapper;
    private final TokenService tokenService;

    public TeacherController(TeacherService service, TeacherMapper mapper, TokenService tokenService) {
        this.service = service;
        this.mapper = mapper;
        this.tokenService = tokenService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addTeacher(@Valid @RequestBody AddTeacherDto newTeacherDto, UriComponentsBuilder uriBuilder) {
        Teacher newTeacher = mapper.toModel(newTeacherDto);
        Teacher added = service.add(newTeacher, newTeacherDto.getRepeatedPassword());

        URI uri = uriBuilder
                .path("/api/teachers/{id}")
                .buildAndExpand(added.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<PaginatedResponse<TeacherViewDto>> getTeachers(
//            @RequestParam(value="isStaresina", required = false) Boolean isStaresina,
            Pageable pageable
    ) {
        Page<Teacher> allTeachers = service.getAll(pageable);
        Page<TeacherViewDto> allTeachersDto = allTeachers.map(mapper::toViewDto);

        PaginatedResponse<TeacherViewDto> responseDto = new PaginatedResponse<>(
                allTeachersDto.getContent(),
                allTeachersDto.getTotalElements(),
                allTeachersDto.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/not-staresina")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<TeacherViewDto>> getNonStaresinaTeachers() {
        List<Teacher> allTeachers = service.getAllNotStaresina();

        List<TeacherViewDto> allTeachersDto = allTeachers
                .stream()
                .map(mapper::toViewDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allTeachersDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<TeacherViewDto> getTeacher(@PathVariable Long id) {
        Teacher found = service.getById(id);
        TeacherViewDto foundDto = mapper.toViewDto(found);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<TeacherViewDto> getProfile() {
        Teacher found = service.getProfile();
        TeacherViewDto foundDto = mapper.toViewDto(found);
        return ResponseEntity.ok(foundDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<UpdateTeacherResponseDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody UpdateTeacherDto changesDto) {
        Teacher original = service.getById(id);
        String originalUsername = original.getUsername();

        Teacher changes = mapper.toModel(changesDto);
        Teacher updated = service.update(id, changes);

        String jwt = "";
        if (!originalUsername.equals(updated.getUsername())) {
            jwt = tokenService.getToken(updated.getUsername());
        }

        TeacherViewDto updatedDto = mapper.toViewDto(updated);
        return ResponseEntity.ok(new UpdateTeacherResponseDto(updatedDto, jwt));
    }

    @PutMapping("/password")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        service.changePassword(
                passwordChangeDto.getOldPassword(),
                passwordChangeDto.getNewPassword(),
                passwordChangeDto.getRepeatedPassword()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
