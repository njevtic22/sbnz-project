package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role", "name", name));
    }
}

