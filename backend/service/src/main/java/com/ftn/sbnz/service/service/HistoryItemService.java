package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.model.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryItemService {
    HistoryItem reportStudent(Long studentId, Report report);

    List<HistoryItem> saveAll(Iterable<HistoryItem> history);

    Page<HistoryItem> getAll(Long studentId, Pageable pageable);
}
