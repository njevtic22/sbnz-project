package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByArchivedFalse();

    Optional<User> findByUsernameAndArchivedFalse(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
