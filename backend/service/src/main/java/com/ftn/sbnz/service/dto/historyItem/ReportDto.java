package com.ftn.sbnz.service.dto.historyItem;

public class ReportDto {
    private Long studentId;
    private String nivoNasilja;

    public ReportDto(Long studentId, String nivoNasilja) {
        this.studentId = studentId;
        this.nivoNasilja = nivoNasilja;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getNivoNasilja() {
        return nivoNasilja;
    }
}
