package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService extends CrudService<Student> {
    Page<Student> getAllForClass(Long classId, Pageable pageable);

    Page<Student> getAllForTeacher(Pageable pageable);

    Student add(Long classId, Student newStudent, String repeatedPassword);

    boolean existsByUsername(String username);

    Student getByUsername(String username);

    Student getProfile();

    void changePassword(String oldPassword, String newPassword, String repeatedPassword);

    Student save(Student toSave);
}
