package com.ftn.sbnz.model.model;

import java.util.Objects;

public class Report {
    private Long id;
    private Student student;
    private NivoNasilja nivoNasilja;

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
