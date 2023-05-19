package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository repository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
    public Admin add(Admin newAdmin) {
        validateEmail(newAdmin.getEmail());
        validateUsername(newAdmin.getUsername());

        Role adminRole = roleService.getByName("ROLE_ADMIN");
        Admin toAdd = new Admin(
                newAdmin.getName(),
                newAdmin.getSurname(),
                newAdmin.getBirthDate(),
                newAdmin.getJmbg(),
                newAdmin.getEmail(),
                newAdmin.getUsername(),
                passwordEncoder.encode(newAdmin.getPassword()),
                false,
                adminRole
        );
        return repository.save(toAdd);
    }

    @Override
    public Admin add(Admin newAdmin, String repeatedPassword) {
        if (!newAdmin.getPassword().equals(repeatedPassword)) {     // passwords are not encoded
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
        return add(newAdmin);
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

    private void validateEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new UniquePropertyException("Email '" + email + "' is already taken.");
        }
    }

    private void validateUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new UniquePropertyException("Username '" + username + "' is already taken.");
        }
    }
}
