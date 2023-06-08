package com.ftn.sbnz.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_generator")
    @SequenceGenerator(name = "school_generator", sequenceName = "school_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Teacher> teachers;

    @OneToMany
    private List<Odeljenje> odeljenja;

    public School() { }

    public School(String name, List<Teacher> teachers, List<Odeljenje> odeljenja) {
        this(null, name, teachers, odeljenja);
    }

    public School(Long id, String name, List<Teacher> teachers, List<Odeljenje> odeljenja) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
        this.odeljenja = odeljenja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return Objects.equals(id, school.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addOdeljenje(Odeljenje odeljenje) {
        this.odeljenja.add(odeljenje);
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Odeljenje> getOdeljenja() {
        return odeljenja;
    }

    public void setOdeljenja(List<Odeljenje> odeljenja) {
        this.odeljenja = odeljenja;
    }
}
