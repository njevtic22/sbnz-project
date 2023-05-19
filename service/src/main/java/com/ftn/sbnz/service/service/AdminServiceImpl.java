package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

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
        Objects.requireNonNull(id, "Id must not be null.");
        return repository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin", id));
    }

    @Override
    public Admin update(Long id, Admin changes) {
        // Without this check, NullPointerException (if changes is null) would be thrown after orm mapping (getById)
        // which will render orm mapping useless
        Objects.requireNonNull(changes, "Admin changes must not be null.");

        Admin exist = getById(id);
        if (!exist.getEmail().equals(changes.getEmail())) {
            validateEmail(changes.getEmail());
        }
        if (!exist.getUsername().equals(changes.getUsername())) {
            validateUsername(changes.getUsername());
        }

        Admin updated = new Admin(
                exist.getId(),
                changes.getName(),
                changes.getSurname(),
                changes.getBirthDate(),
                changes.getJmbg(),
                changes.getEmail(),
                changes.getUsername(),
                exist.getPassword(),
                exist.isArchived(),
                exist.getRole()
        );
        return repository.save(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Admin id must not be null");

        if (!repository.existsByIdAndArchivedFalse(id)) {
            throw new EntityNotFoundException("Admin", id);
        }

        int rowsAffected = repository.archiveById(id);
        if (rowsAffected != 1) {
            throw new MultipleDeletedRowsException("Admins");
        }
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
