package com.ftn.sbnz.model.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class Teacher extends User {
    public Teacher() { }

    public Teacher(String name, String surname, LocalDate birthDate, String email, String username, String password, boolean archived, Role role) {
        super(name, surname, birthDate, email, username, password, archived, role);
    }

    public Teacher(Long id, String name, String surname, LocalDate birthDate, String email, String username, String password, boolean archived, Role role) {
        super(id, name, surname, birthDate, email, username, password, archived, role);
    }
}
