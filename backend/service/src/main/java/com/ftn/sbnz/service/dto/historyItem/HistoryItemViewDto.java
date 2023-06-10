package com.ftn.sbnz.service.dto.historyItem;

import java.time.LocalDate;


public class HistoryItemViewDto {
    private final Long id;
    private final String nivoNasilja;
    private final VdpViewDto vdp;
    private final SanctionViewDto sanction;
    private final LocalDate reportDate;

    public HistoryItemViewDto(Long id, String nivoNasilja, VdpViewDto vdp, SanctionViewDto sanction, LocalDate reportDate) {
        this.id = id;
        this.nivoNasilja = nivoNasilja;
        this.vdp = vdp;
        this.sanction = sanction;
        this.reportDate = reportDate;
    }

    public Long getId() {
        return id;
    }

    public String getNivoNasilja() {
        return nivoNasilja;
    }

    public VdpViewDto getVdp() {
        return vdp;
    }

    public SanctionViewDto getSanction() {
        return sanction;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
