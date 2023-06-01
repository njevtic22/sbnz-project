package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByArchivedFalse(Pageable pageable);

    Optional<Student> findByIdAndArchivedFalse(Long id);

    Optional<Student> findByUsernameAndArchivedFalse(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByIdAndArchivedFalse(Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Student s set s.archived = true where s.id = :id")
    int archiveById(Long id);
}
