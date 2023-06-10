package com.ftn.sbnz.service.repository;


import com.ftn.sbnz.model.model.HistoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
    Page<HistoryItem> findAllByStudentId(Long studentId, Pageable pageable);
}
