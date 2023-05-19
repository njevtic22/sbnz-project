package com.ftn.sbnz.service.dto.user;

import java.time.LocalDate;

public abstract class UserViewDto {
    private final Long id;
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final String jmbg;
    private final String email;
    private final String username;
    private final String role;

    public UserViewDto(Long id, String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.jmbg = jmbg;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}