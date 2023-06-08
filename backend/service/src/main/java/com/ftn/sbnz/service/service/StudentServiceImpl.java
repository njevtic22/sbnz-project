package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.NivoSklonostiKaNasilju;
import com.ftn.sbnz.model.model.Role;
import com.ftn.sbnz.model.model.Student;
import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import com.ftn.sbnz.service.repository.StudentRepository;
import com.ftn.sbnz.service.security.AuthenticationService;
import com.ftn.sbnz.service.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public StudentServiceImpl(StudentRepository repository, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, UserService userService) {
        this.repository = repository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Student getByUsername(String username) {
        Strings.requireNonBlank(username, "Username must not be blank.");
        return repository.findByUsernameAndArchivedFalse(username)
                .orElseThrow(() -> new EntityNotFoundException("Student", "username", username));
    }

    @Override
    public Student getProfile() {
        User authenticated = authenticationService.getAuthenticated();
        return getByUsername(authenticated.getUsername());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String repeatedPassword) {
        User authenticated = authenticationService.getAuthenticated();
        Student existingStudent = getByUsername(authenticated.getUsername());

        validatePasswordMatch(existingStudent, oldPassword, newPassword, repeatedPassword);

        Student changed = new Student(
                existingStudent.getId(),
                existingStudent.getName(),
                existingStudent.getSurname(),
                existingStudent.getBirthDate(),
                existingStudent.getEmail(),
                existingStudent.getUsername(),
                passwordEncoder.encode(newPassword),
                existingStudent.isArchived(),
                existingStudent.getRole(),
                existingStudent.getNivoSklonosti(),
                existingStudent.getHistory()
        );
        repository.save(changed);
    }

    @Override
    public Student save(Student toSave) {
        return repository.save(toSave);
    }

    @Override
    public Student add(Student newStudent, String repeatedPassword) {
        if (!newStudent.getPassword().equals(repeatedPassword)) {     // passwords are not encoded
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
        return add(newStudent);
    }

    @Override
    public Student add(Student newStudent) {
        validateEmail(newStudent.getEmail());
        validateUsername(newStudent.getUsername());

        Role studentRole = roleService.getByName("ROLE_STUDENT");
        Student toAdd = new Student(
                newStudent.getName(),
                newStudent.getSurname(),
                newStudent.getBirthDate(),
                newStudent.getEmail(),
                newStudent.getUsername(),
                passwordEncoder.encode(newStudent.getPassword()),
                false,
                studentRole,
                NivoSklonostiKaNasilju.NEMA,
                new ArrayList<>()
        );
        return repository.save(toAdd);
    }

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return repository.findAllByArchivedFalse(pageable);
    }

    @Override
    public Page<Student> getAllForClass(Long classId, Pageable pageable) {
        return repository.findAllByClassId(classId, pageable);
    }

    @Override
    public Page<Student> getAllForTeacher(Long teacherId, Pageable pageable) {
        return repository.findAllByTeacherId(teacherId, pageable);
    }

    @Override
    public Student getById(Long id) {
        Objects.requireNonNull(id, "Id must not be null.");
        return repository.findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    @Override
    public Student update(Long id, Student changes) {
        // Without this check, NullPointerException (if changes is null) would be thrown after orm mapping (getById)
        // which will render orm mapping useless
        Objects.requireNonNull(changes, "Student changes must not be null.");

        Student exist = getById(id);
        if (!exist.getEmail().equals(changes.getEmail())) {
            validateEmail(changes.getEmail());
        }
        if (!exist.getUsername().equals(changes.getUsername())) {
            validateUsername(changes.getUsername());
        }
        Student updated = new Student(
                exist.getId(),
                changes.getName(),
                changes.getSurname(),
                changes.getBirthDate(),
                changes.getEmail(),
                changes.getUsername(),
                exist.getPassword(),
                exist.isArchived(),
                exist.getRole(),
                exist.getNivoSklonosti(),
                exist.getHistory()
        );
        return repository.save(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Objects.requireNonNull(id, "Student id must not be null");

        if (!repository.existsByIdAndArchivedFalse(id)) {
            throw new EntityNotFoundException("Student", id);
        }

        int rowsAffected = repository.archiveById(id);
        if (rowsAffected != 1) {
            throw new MultipleDeletedRowsException("Students");
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

    private void validatePasswordMatch(Student existingStudent, String oldPassword, String newPassword, String repeatedPassword) {
        if (!passwordEncoder.matches(oldPassword, existingStudent.getPassword())) {
            throw new InvalidPasswordException("Incorrect password.");
        }

        if (!newPassword.equals(repeatedPassword)) {
            throw new InvalidPasswordException("New password and repeated password do not match.");
        }
    }
}
