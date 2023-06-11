package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.service.dto.historyItem.HistoryItemViewDto;
import com.ftn.sbnz.service.dto.historyItem.NivoNasiljaDto;
import com.ftn.sbnz.service.dto.historyItem.OblikNasiljaDto;
import com.ftn.sbnz.service.dto.historyItem.SanctionViewDto;
import com.ftn.sbnz.service.dto.historyItem.TipNasiljaDto;
import com.ftn.sbnz.service.dto.historyItem.VdpViewDto;
import org.springframework.stereotype.Component;

@Component
public class HistoryItemMapper {
    public HistoryItemViewDto toViewDto(HistoryItem item) {
        return new HistoryItemViewDto(
                item.getId(),
                new NivoNasiljaDto(item.getNivoNasilja().toString(), item.getNivoNasilja().getDescription()),
                new TipNasiljaDto(item.getTipNasilja().toString(), item.getTipNasilja().getDescription()),
                new OblikNasiljaDto(item.getOblikNasilja().toString(), item.getOblikNasilja().getDescription()),
                item.getOpis(),
                new VdpViewDto(item.getVdp().toString(), item.getVdp().getDescription()),
                new SanctionViewDto(
                        item.getSanction() == null ? null : item.getSanction().toString(),
                        item.getSanction() == null ? null : item.getSanction().getDescription()
                ),
                item.getReportDate()
        );
    }
}
