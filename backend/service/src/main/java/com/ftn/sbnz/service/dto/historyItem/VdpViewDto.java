package com.ftn.sbnz.service.dto.historyItem;

public class VdpViewDto {
    private final String value;
    private final String description;

    public VdpViewDto(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
