package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Admin;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.AdminRepository;
import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public AdminServiceImpl(AdminRepository repository, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Admin getByUsername(String username) {
        Strings.requireNonBlank(username, "Username must not be blank.");
        return repository.findByUsernameAndArchivedFalse(username)
                .orElseThrow(() -> new EntityNotFoundException("Admin", "username", username));
    }

    @Override
    public Admin getProfile() {
        User authenticated = authenticationService.getAuthenticated();
        return getByUsername(authenticated.getUsername());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String repeatedPassword) {
        User authenticated = authenticationService.getAuthenticated();
        Admin existingAdmin = getByUsername(authenticated.getUsername());

        validatePasswordMatch(existingAdmin, oldPassword, newPassword, repeatedPassword);

        Admin changed = new Admin(
                existingAdmin.getId(),
                existingAdmin.getName(),
                existingAdmin.getSurname(),
                existingAdmin.getBirthDate(),
                existingAdmin.getJmbg(),
                existingAdmin.getEmail(),
                existingAdmin.getUsername(),
                passwordEncoder.encode(newPassword),
                existingAdmin.isArchived(),
                existingAdmin.getRole()
        );
        repository.save(changed);
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

    private void validatePasswordMatch(Admin existingAdmin, String oldPassword, String newPassword, String repeatedPassword) {
        if (!passwordEncoder.matches(oldPassword, existingAdmin.getPassword())) {
            throw new InvalidPasswordException("Incorrect password.");
        }

        if (!newPassword.equals(repeatedPassword)) {
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
    }
}
