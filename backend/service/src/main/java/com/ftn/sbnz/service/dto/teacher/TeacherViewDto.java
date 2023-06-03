package com.ftn.sbnz.service.dto.teacher;

import com.ftn.sbnz.service.dto.user.UserViewDto;

import java.time.LocalDate;

public class TeacherViewDto extends UserViewDto {
    public TeacherViewDto(Long id, String name, String surname, LocalDate birthDate, String email, String username, String role) {
        super(id, name, surname, birthDate, email, username, role);
    }
}
