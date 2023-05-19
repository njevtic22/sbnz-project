package com.ftn.sbnz.service.service;

import com.ftn.sbnz.model.model.Admin;

public interface AdminService extends CrudService<Admin> {
    Admin add(Admin newAdmin, String repeatedPassword);

    boolean existsByUsername(String username);

    Admin getByUsername(String username);

    Admin getProfile();

    void changePassword(String oldPassword, String newPassword, String repeatedPassword);
}
