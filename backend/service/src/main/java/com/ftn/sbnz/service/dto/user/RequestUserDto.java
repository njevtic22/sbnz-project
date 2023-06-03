package com.ftn.sbnz.service.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public abstract class RequestUserDto {
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotBlank(message = "Surname must not be blank.")
    private String surname;

    @NotNull(message = "Birth date must not be blank.")
    private LocalDate birthDate;

    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Email must be properly formed.")
    private String email;

    @NotBlank(message = "Username must not be blank.")
    private String username;

    public RequestUserDto() { }

    public RequestUserDto(String name, String surname, LocalDate birthDate, String email, String username) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}