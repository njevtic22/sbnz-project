package com.ftn.sbnz.service.dto.historyItem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReportDto {
    @NotNull(message = "Student id must not be null.")
    @Positive(message = "Student id must be positive long.")
    private Long studentId;

    @NotBlank(message = "Nivo nasilja must not be blank.")
    private String nivoNasilja;

    @NotBlank(message = "Tip nasilja must not be blank.")
    private String tipNasilja;

    @NotBlank(message = "Oblik nasilja must not be blank.")
    private String oblikNasilja;

    @NotBlank(message = "Opis nasilja must not be blank.")
    private String opis;

    public ReportDto() { }

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
