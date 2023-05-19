package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Page<Admin> findAllByArchivedFalse(Pageable pageable);

    Optional<Admin> findByIdAndArchivedFalse(Long id);

    Optional<Admin> findByUsernameAndArchivedFalse(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByIdAndArchivedFalse(Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Admin a set a.archived = true where a.id = :id")
    int archiveById(Long id);
}
