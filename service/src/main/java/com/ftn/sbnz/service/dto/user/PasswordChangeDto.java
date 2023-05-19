package com.ftn.sbnz.service.dto.user;

import com.ftn.sbnz.service.core.validation.annotation.Password;

import javax.validation.constraints.NotBlank;

public class PasswordChangeDto {
    //    @Password(field = "Old password")
    @NotBlank(message = "Old password must not be blank.")
    private String oldPassword;

    @Password(field = "New password")
    private String newPassword;

    //    @Password(field = "Repeated password")
    @NotBlank(message = "Repeated password must not be blank.")
    private String repeatedPassword;

    public PasswordChangeDto() { }

    public PasswordChangeDto(String oldPassword, String newPassword, String repeatedPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatedPassword = repeatedPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }
}
