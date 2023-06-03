package com.ftn.sbnz.model.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() { }

    public Admin(String name, String surname, LocalDate birthDate, String email, String username, String password, boolean archived, Role role) {
        super(null, name, surname, birthDate, email, username, password, archived, role);
    }

    public Admin(Long id, String name, String surname, LocalDate birthDate, String email, String username, String password, boolean archived, Role role) {
        super(id, name, surname, birthDate, email, username, password, archived, role);
    }
}
