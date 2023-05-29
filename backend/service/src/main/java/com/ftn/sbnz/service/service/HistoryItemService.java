package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.HistoryItem;

import java.util.List;

public interface HistoryItemService {
    List<HistoryItem> saveAll(Iterable<HistoryItem> history);
}
