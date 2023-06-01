package com.ftn.sbnz.service.dto.student;

public class UpdateStudentResponseDto {
    private final StudentViewDto updated;
    private final String token;

    public UpdateStudentResponseDto(StudentViewDto updated, String token) {
        this.updated = updated;
        this.token = token;
    }

    public StudentViewDto getUpdated() {
        return updated;
    }

    public String getToken() {
        return token;
    }
}
