package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.service.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final RoleService roleService;

    public AdminServiceImpl(AdminRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public Admin getByUsername(String username) {
        return null;
    }

    @Override
    public Admin getProfile() {
        return null;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String repeatedPassword) {

    }

    @Override
    public Admin add(Admin newT) {
        return null;
    }

    @Override
    public Admin add(Admin newAdmin, String repeatedPassword) {
        return null;
    }

    @Override
    public Page<Admin> getAll(Pageable pageable) {
        return repository.findAllByArchivedFalse(pageable);
    }

    @Override
    public Admin getById(Long id) {
        return null;
    }

    @Override
    public Admin update(Long id, Admin changes) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
