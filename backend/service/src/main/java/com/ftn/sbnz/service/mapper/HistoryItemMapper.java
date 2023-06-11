package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.service.dto.historyItem.HistoryItemViewDto;
import com.ftn.sbnz.service.dto.historyItem.SanctionViewDto;
import com.ftn.sbnz.service.dto.historyItem.VdpViewDto;
import org.springframework.stereotype.Component;

@Component
public class HistoryItemMapper {
    public HistoryItemViewDto toViewDto(HistoryItem item) {
        return new HistoryItemViewDto(
                item.getId(),
                item.getNivoNasilja().toString(),
                item.getTipNasilja().toString(),
                item.getOblikNasilja().toString(),
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
