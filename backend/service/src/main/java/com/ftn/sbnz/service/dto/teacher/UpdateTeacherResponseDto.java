package com.ftn.sbnz.service.dto.teacher;

public class UpdateTeacherResponseDto {
    private final TeacherViewDto updated;
    private final String token;

    public UpdateTeacherResponseDto(TeacherViewDto updated, String token) {
        this.updated = updated;
        this.token = token;
    }

    public TeacherViewDto getUpdated() {
        return updated;
    }

    public String getToken() {
        return token;
    }
}
