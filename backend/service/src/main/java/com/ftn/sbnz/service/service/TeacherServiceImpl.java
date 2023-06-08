package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Odeljenje;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.model.model.School;
import com.ftn.sbnz.model.model.Teacher;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.TeacherRepository;
import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final SchoolService schoolService;

    public TeacherServiceImpl(
            TeacherRepository repository,
            RoleService roleService,
            PasswordEncoder passwordEncoder,
            AuthenticationService authenticationService,
            UserService userService,
            SchoolService schoolService
    ) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Teacher getByUsername(String username) {
        Strings.requireNonBlank(username, "Username must not be blank.");
        return repository.findByUsernameAndArchivedFalse(username)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", "username", username));
    }

    @Override
    public Teacher getProfile() {
        User authenticated = authenticationService.getAuthenticated();
        return getByUsername(authenticated.getUsername());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String repeatedPassword) {
        User authenticated = authenticationService.getAuthenticated();
        Teacher existingTeacher = getByUsername(authenticated.getUsername());

        validatePasswordMatch(existingTeacher, oldPassword, newPassword, repeatedPassword);

        Teacher changed = new Teacher(
                existingTeacher.getId(),
                existingTeacher.getName(),
                existingTeacher.getSurname(),
                existingTeacher.getBirthDate(),
                existingTeacher.getEmail(),
                existingTeacher.getUsername(),
                passwordEncoder.encode(newPassword),
                existingTeacher.isArchived(),
                existingTeacher.getRole()
        );
        repository.save(changed);
    }

    @Override
    public Teacher add(Teacher newTeacher) {
        validateEmail(newTeacher.getEmail());
        validateUsername(newTeacher.getUsername());

        Role teacherRole = roleService.getByName("ROLE_TEACHER");
        Teacher toAdd = new Teacher(
                newTeacher.getName(),
                newTeacher.getSurname(),
                newTeacher.getBirthDate(),
                newTeacher.getEmail(),
                newTeacher.getUsername(),
                passwordEncoder.encode(newTeacher.getPassword()),
                false,
                teacherRole
        );
        Teacher added = repository.save(toAdd);

        School school = schoolService.getById();
        school.addTeacher(added);
        schoolService.save(school);

        return added;
    }

    @Override
    public Teacher add(Teacher newTeacher, String repeatedPassword) {
        if (!newTeacher.getPassword().equals(repeatedPassword)) {     // passwords are not encoded
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
        return add(newTeacher);
    }

    @Override
    public Page<Teacher> getAll(Pageable pageable) {
        return repository.findAllByArchivedFalse(pageable);
    }

    @Override
    public List<Teacher> getAllNotStaresina() {
        List<Teacher> allTeachers = getAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by("id").ascending())).getContent();

        School school = schoolService.getById();

        ArrayList<Teacher> notStaresinaTeachers = new ArrayList<>(allTeachers.size() / 2);
        for (Teacher teacher : allTeachers) {
            if (!isStaresina(teacher, school)) {
                notStaresinaTeachers.add(teacher);
            }
        }

        return notStaresinaTeachers;
    }

    private boolean isStaresina(Teacher teacher, School school) {
        for (Odeljenje odeljenje : school.getOdeljenja()) {
            if (!odeljenje.isArchived() && odeljenje.getStaresina().equals(teacher)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Teacher getById(Long id) {
        Objects.requireNonNull(id, "Id must not be null");
        return repository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));
    }

    @Override
    public Teacher update(Long id, Teacher changes) {
        // Without this check, NullPointerException (if changes is null) would be thrown after orm mapping (getById)
        // which will render orm mapping useless
        Objects.requireNonNull(changes, "Teacher changes must not be null.");

        Teacher exist = getById(id);
        if (!exist.getEmail().equals(changes.getEmail())) {
            validateEmail(changes.getEmail());
        }
        if (!exist.getUsername().equals(changes.getUsername())) {
            validateUsername(changes.getUsername());
        }

        Teacher updated = new Teacher(
                exist.getId(),
                changes.getName(),
                changes.getSurname(),
                changes.getBirthDate(),
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
        Objects.requireNonNull(id, "Teacher id must not be null");

        if (!repository.existsByIdAndArchivedFalse(id)) {
            throw new EntityNotFoundException("Teacher", id);
        }

        int rowsAffected = repository.archiveById(id);
        if (rowsAffected != 1) {
            throw new MultipleDeletedRowsException("Teachers");
        }
    }

    private void validateEmail(String email) {
        if (userService.existsByEmail(email)) {
            throw new UniquePropertyException("Email '" + email + "' is already taken.");
        }
    }

    private void validateUsername(String username) {
        if (userService.existsByUsername(username)) {
            throw new UniquePropertyException("Username '" + username + "' is already taken.");
        }
    }

    private void validatePasswordMatch(Teacher existingTeacher, String oldPassword, String newPassword, String repeatedPassword) {
        if (!passwordEncoder.matches(oldPassword, existingTeacher.getPassword())) {
            throw new InvalidPasswordException("Incorrect password.");
        }

        if (!newPassword.equals(repeatedPassword)) {
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
    }
}
