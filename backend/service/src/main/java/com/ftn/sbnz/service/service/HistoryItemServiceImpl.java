package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.HistoryItem;
import com.ftn.sbnz.service.repository.HistoryItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryItemServiceImpl implements HistoryItemService {
    private final HistoryItemRepository repository;

    public HistoryItemServiceImpl(HistoryItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HistoryItem> saveAll(Iterable<HistoryItem> history) {
        return repository.saveAll(history);
    }

    @Override
    public Page<HistoryItem> getAll(Long studentId, Pageable pageable) {
        return repository.findAllByStudentId(studentId, pageable);
    }
}
