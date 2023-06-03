package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.model.Odeljenje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdeljenjeRepository extends JpaRepository<Odeljenje, Long> {
    Page<Odeljenje> findAllByArchivedFalse(Pageable pageable);

    Optional<Odeljenje> findByIdAndArchivedFalse(Long id);

    boolean existsByNazivAndArchivedFalse(String username);

    boolean existsByIdAndArchivedFalse(Long id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Odeljenje o set o.archived = true where o.id = :id")
    int archiveById(Long id);
}
