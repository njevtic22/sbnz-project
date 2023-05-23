package com.ftn.sbnz.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sanction_for_students")
public class SanctionForStudent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sanction_for_student_generator")
    @SequenceGenerator(name = "sanction_for_student_generator", sequenceName = "sanction_for_student_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private Sanction sanction;

    public SanctionForStudent() { }

    public SanctionForStudent(Student student, Sanction sanction) {
        this(null, student, sanction);
    }

    public SanctionForStudent(Long id, Student student, Sanction sanction) {
        this.id = id;
        this.student = student;
        this.sanction = sanction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SanctionForStudent)) return false;
        SanctionForStudent that = (SanctionForStudent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Student getStudent() {
        return student;
    }

    public void setStudentId(Student student) {
        this.student = student;
    }

    public Sanction getSanction() {
        return sanction;
    }

    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }
}
