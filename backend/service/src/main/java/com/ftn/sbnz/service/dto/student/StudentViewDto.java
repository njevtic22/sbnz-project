package com.ftn.sbnz.service.dto.student;

import com.ftn.sbnz.model.model.NivoSklonostiKaNasilju;
import com.ftn.sbnz.service.dto.user.UserViewDto;

import java.time.LocalDate;

public class StudentViewDto extends UserViewDto {
    private final String nivoSklonosti;

    public StudentViewDto(Long id, String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String role, String nivoSklonosti) {
        super(id, name, surname, birthDate, jmbg, email, username, role);
        this.nivoSklonosti = nivoSklonosti;
    }

}
