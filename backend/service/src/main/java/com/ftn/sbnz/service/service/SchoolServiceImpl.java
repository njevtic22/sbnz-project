package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.School;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository repository;

    public SchoolServiceImpl(SchoolRepository repository) {
        this.repository = repository;
    }

    @Override
    public School getById() {
        return repository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("School", 1L));
    }

    @Override
    public School save(School school) {
        return repository.save(school);
    }
}
