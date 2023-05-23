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
@Table(name = "reports")
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_generator")
    @SequenceGenerator(name = "report_generator", sequenceName = "report_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private NivoNasilja nivoNasilja;

    public Report() { }

    public Report(Student student, NivoNasilja nivoNasilja) {
        this(null, student, nivoNasilja);
    }

    public Report(Long id, Student student, NivoNasilja nivoNasilja) {
        this.id = id;
        this.student = student;
        this.nivoNasilja = nivoNasilja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id);
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

    public NivoNasilja getNivoNasilja() {
        return nivoNasilja;
    }

    public void setNivoNasilja(NivoNasilja nivoNasilja) {
        this.nivoNasilja = nivoNasilja;
    }
}
