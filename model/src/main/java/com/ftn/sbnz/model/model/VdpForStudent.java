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
@Table(name = "vdp_for_students")
public class VdpForStudent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vdp_for_student_generator")
    @SequenceGenerator(name = "vdp_for_student_generator", sequenceName = "vdp_for_student_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private Vdp vdp;

    public VdpForStudent() { }

    public VdpForStudent(Student student, Vdp vdp) {
        this(null, student, vdp);
    }

    public VdpForStudent(Long id, Student student, Vdp vdp) {
        this.id = id;
        this.student = student;
        this.vdp = vdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VdpForStudent)) return false;
        VdpForStudent that = (VdpForStudent) o;
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

    public Vdp getVdp() {
        return vdp;
    }

    public void setVdp(Vdp vdp) {
        this.vdp = vdp;
    }
}
