package com.ftn.sbnz.service.repository;


import com.ftn.sbnz.model.model.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {

}
