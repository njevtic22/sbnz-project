package com.ftn.sbnz.model.model;

import java.util.Objects;

public class Report {
    private Long id;
    private Student student;
    private NivoNasilja nivoNasilja;
    private TipNasilja tipNasilja;
    private OblikNasilja oblikNasilja;
    private String opis;

    public Report(Long id, Student student, NivoNasilja nivoNasilja, TipNasilja tipNasilja, OblikNasilja oblikNasilja, String opis) {
        this.id = id;
        this.student = student;
        this.nivoNasilja = nivoNasilja;
        this.tipNasilja = tipNasilja;
        this.oblikNasilja = oblikNasilja;
        this.opis = opis;
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

    public void setStudent(Student student) {
        this.student = student;
    }

    public NivoNasilja getNivoNasilja() {
        return nivoNasilja;
    }

    public void setNivoNasilja(NivoNasilja nivoNasilja) {
        this.nivoNasilja = nivoNasilja;
    }

    public TipNasilja getTipNasilja() {
        return tipNasilja;
    }

    public void setTipNasilja(TipNasilja tipNasilja) {
        this.tipNasilja = tipNasilja;
    }

    public OblikNasilja getOblikNasilja() {
        return oblikNasilja;
    }

    public void setOblikNasilja(OblikNasilja oblikNasilja) {
        this.oblikNasilja = oblikNasilja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
