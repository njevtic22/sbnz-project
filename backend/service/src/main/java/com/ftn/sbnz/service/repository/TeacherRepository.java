package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findAllByArchivedFalse(Pageable pageable);

    Optional<Teacher> findByIdAndArchivedFalse(Long id);

    Optional<Teacher> findByUsernameAndArchivedFalse(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByIdAndArchivedFalse(Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Teacher t set t.archived = true where t.id = :id")
    int archiveById(Long id);
}
