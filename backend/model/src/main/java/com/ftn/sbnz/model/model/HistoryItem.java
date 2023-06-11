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
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "history_items")
public class HistoryItem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_item_generator")
    @SequenceGenerator(name = "history_item_generator", sequenceName = "history_item_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private NivoNasilja nivoNasilja;

    @Enumerated(EnumType.STRING)
    private TipNasilja tipNasilja;

    @Enumerated(EnumType.STRING)
    private OblikNasilja oblikNasilja;

    @Column(length = 1000)
    private String opis;

    @Enumerated(EnumType.STRING)
    private Vdp vdp;

    @Enumerated(EnumType.STRING)
    private Sanction sanction;

    @Column(nullable = false)
    private LocalDate reportDate;

    public HistoryItem() { }

    public HistoryItem(Student student, NivoNasilja nivoNasilja, TipNasilja tipNasilja, OblikNasilja oblikNasilja, String opis, Vdp vdp, Sanction sanction, LocalDate reportDate) {
        this(null, student, nivoNasilja, tipNasilja, oblikNasilja, opis, vdp, sanction, reportDate);
    }

    public HistoryItem(Long id, Student student, NivoNasilja nivoNasilja, TipNasilja tipNasilja, OblikNasilja oblikNasilja, String opis, Vdp vdp, Sanction sanction, LocalDate reportDate) {
        this.id = id;
        this.student = student;
        this.nivoNasilja = nivoNasilja;
        this.tipNasilja = tipNasilja;
        this.oblikNasilja = oblikNasilja;
        this.opis = opis;
        this.vdp = vdp;
        this.sanction = sanction;
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoryItem)) return false;
        HistoryItem that = (HistoryItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "id=" + id +
                ", student=" + student +
                ", nivoNasilja=" + nivoNasilja +
                ", tipNasilja=" + tipNasilja +
                ", oblikNasilja=" + oblikNasilja +
                ", opis='" + opis + '\'' +
                ", vdp=" + vdp +
                ", sanction=" + sanction +
                ", reportDate=" + reportDate +
                '}';
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

    public Vdp getVdp() {
        return vdp;
    }

    public void setVdp(Vdp vdp) {
        this.vdp = vdp;
    }

    public Sanction getSanction() {
        return sanction;
    }

    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }
}
