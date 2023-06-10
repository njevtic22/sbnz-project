package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.service.core.PaginatedResponse;
import com.ftn.sbnz.service.dto.student.AddStudentDto;
import com.ftn.sbnz.service.dto.student.StudentViewDto;
import com.ftn.sbnz.service.dto.student.UpdateStudentDto;
import com.ftn.sbnz.service.dto.student.UpdateStudentResponseDto;
import com.ftn.sbnz.service.dto.user.PasswordChangeDto;
import com.ftn.sbnz.service.mapper.StudentMapper;
import com.ftn.sbnz.service.security.TokenService;
import com.ftn.sbnz.service.service.StudentService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    private final StudentMapper mapper;
    private final TokenService tokenService;

    public StudentController(StudentService service, StudentMapper mapper, TokenService tokenService) {
        this.service = service;
        this.mapper = mapper;
        this.tokenService = tokenService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addStudent(
            @RequestParam Long classId,
            @Valid @RequestBody AddStudentDto newStudentDto,
            UriComponentsBuilder uriBuilder
    ) {
        Student newStudent = mapper.toModel(newStudentDto);
        Student added = service.add(classId, newStudent, newStudentDto.getRepeatedPassword());

        URI uri = uriBuilder
                .path("/api/students/{id}")
                .buildAndExpand(added.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

//    https://stackoverflow.com/questions/53130695/what-is-a-better-way-to-design-an-api-for-mutually-exclusive-request-parameters
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<PaginatedResponse<StudentViewDto>> getStudents(
            @RequestParam(required = false) Long classId,
            Pageable pageable
    ) {
        Page<Student> allStudents = null;
        if (classId != null) {
            allStudents = service.getAllForClass(classId, pageable);
        } else {
            allStudents = service.getAll(pageable);
        }

        Page<StudentViewDto> allStudentsDto = allStudents.map(mapper::toViewDto);

        PaginatedResponse<StudentViewDto> responseDto = new PaginatedResponse<>(
                allStudentsDto.getContent(),
                allStudentsDto.getTotalElements(),
                allStudentsDto.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/for-teacher")
    @PreAuthorize("hasAnyRole('TEACHER')")
    public ResponseEntity<PaginatedResponse<StudentViewDto>> getStudentsForTeacher(Pageable pageable) {
        Page<Student> allStudents = service.getAllForTeacher(pageable);
        Page<StudentViewDto> allStudentsDto = allStudents.map(mapper::toViewDto);

        PaginatedResponse<StudentViewDto> responseDto = new PaginatedResponse<>(
                allStudentsDto.getContent(),
                allStudentsDto.getTotalElements(),
                allStudentsDto.getTotalPages()
        );
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<StudentViewDto> getStudent(@PathVariable Long id) {
        Student found = service.getById(id);
        StudentViewDto foundDto = mapper.toViewDto(found);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentViewDto> getProfile() {
        Student found = service.getProfile();
        StudentViewDto foundDto = mapper.toViewDto(found);
        return ResponseEntity.ok(foundDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentDto changesDto) {
        Student original = service.getById(id);
        String originalUsername = original.getUsername();

        Student changes = mapper.toModel(changesDto);
        Student updated = service.update(id, changes);

        String jwt = "";
        if (!originalUsername.equals(updated.getUsername())) {
            jwt = tokenService.getToken(updated.getUsername());
        }

        StudentViewDto updatedDto = mapper.toViewDto(updated);
        return ResponseEntity.ok(new UpdateStudentResponseDto(updatedDto, jwt));
    }

    @PutMapping("/password")
    @PreAuthorize("hasRole('STUDENT')")
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
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
