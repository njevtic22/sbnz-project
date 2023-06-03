package com.ftn.sbnz.service.dto.admin;

import com.ftn.sbnz.service.dto.user.UserViewDto;

import java.time.LocalDate;

public class AdminViewDto extends UserViewDto {
    public AdminViewDto(Long id, String name, String surname, LocalDate birthDate, String email, String username, String role) {
        super(id, name, surname, birthDate, email, username, role);
    }
}
