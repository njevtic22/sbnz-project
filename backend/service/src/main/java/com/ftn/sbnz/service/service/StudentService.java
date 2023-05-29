package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Student;

public interface StudentService extends CrudService<Student> {
    Student add(Student newStudent, String repeatedPassword);

    boolean existsByUsername(String username);

    Student getByUsername(String username);

    Student getProfile();

    void changePassword(String oldPassword, String newPassword, String repeatedPassword);

    Student save(Student toSave);
}
