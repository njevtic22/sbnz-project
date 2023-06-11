package com.ftn.sbnz.service.dto.historyItem;

import java.time.LocalDate;


public class HistoryItemViewDto {
    private final Long id;
    private final NivoNasiljaDto nivoNasilja;
    private final TipNasiljaDto tipNasilja;
    private final OblikNasiljaDto oblikNasilja;
    private final String opis;
    private final VdpViewDto vdp;
    private final SanctionViewDto sanction;
    private final LocalDate reportDate;

    public HistoryItemViewDto(Long id, NivoNasiljaDto nivoNasilja, TipNasiljaDto tipNasilja, OblikNasiljaDto oblikNasilja, String opis, VdpViewDto vdp, SanctionViewDto sanction, LocalDate reportDate) {
        this.id = id;
        this.nivoNasilja = nivoNasilja;
        this.tipNasilja = tipNasilja;
        this.oblikNasilja = oblikNasilja;
        this.opis = opis;
        this.vdp = vdp;
        this.sanction = sanction;
        this.reportDate = reportDate;
    }

    public Long getId() {
        return id;
    }

    public NivoNasiljaDto getNivoNasilja() {
        return nivoNasilja;
    }

    public TipNasiljaDto getTipNasilja() {
        return tipNasilja;
    }

    public OblikNasiljaDto getOblikNasilja() {
        return oblikNasilja;
    }

    public String getOpis() {
        return opis;
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
