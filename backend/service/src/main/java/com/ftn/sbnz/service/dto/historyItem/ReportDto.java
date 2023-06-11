package com.ftn.sbnz.service.dto.historyItem;

public class ReportDto {
    private Long studentId;
    private String nivoNasilja;
    private String tipNasilja;
    private String oblikNasilja;
    private String opis;

    public ReportDto(Long studentId, String nivoNasilja, String tipNasilja, String oblikNasilja, String opis) {
        this.studentId = studentId;
        this.nivoNasilja = nivoNasilja;
        this.tipNasilja = tipNasilja;
        this.oblikNasilja = oblikNasilja;
        this.opis = opis;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getNivoNasilja() {
        return nivoNasilja;
    }

    public String getTipNasilja() {
        return tipNasilja;
    }

    public String getOblikNasilja() {
        return oblikNasilja;
    }

    public String getOpis() {
        return opis;
    }
}
