package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Teacher;

import java.util.List;

public interface TeacherService extends CrudService<Teacher> {
    Teacher add(Teacher newTeacher, String repeatedPassword);

    List<Teacher> getAllNotStaresina();

    boolean existsByUsername(String username);

    Teacher getByUsername(String username);

    Teacher getProfile();

    void changePassword(String oldPassword, String newPassword, String repeatedPassword);
}
