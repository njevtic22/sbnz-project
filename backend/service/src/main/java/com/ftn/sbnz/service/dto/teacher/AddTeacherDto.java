package com.ftn.sbnz.service.dto.teacher;

import com.ftn.sbnz.service.core.validation.annotation.Password;
import com.ftn.sbnz.service.dto.user.RequestUserDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AddTeacherDto extends RequestUserDto {
    @Password
    private String password;

    //    @Password(field = "Repeated password")
    @NotBlank(message = "Repeated password must not be blank.")
    private String repeatedPassword;

    public AddTeacherDto() {
        super();
    }

    public AddTeacherDto(String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String password, String repeatedPassword) {
        super(name, surname, birthDate, jmbg, email, username);
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }
}
