package com.ftn.sbnz.service.dto.admin;

public class UpdateAdminResponseDto {
    private final AdminViewDto updated;
    private final String token;

    public UpdateAdminResponseDto(AdminViewDto updated, String token) {
        this.updated = updated;
        this.token = token;
    }

    public AdminViewDto getUpdated() {
        return updated;
    }

    public String getToken() {
        return token;
    }
}
