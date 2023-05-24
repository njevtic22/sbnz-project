package com.ftn.sbnz.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    @OneToOne
    private Report report;

    @Enumerated(EnumType.STRING)
    private Vdp vdp;

    @Enumerated(EnumType.STRING)
    private Sanction sanction;

    public HistoryItem() { }

    public HistoryItem(Student student, Report report, Vdp vdp, Sanction sanction) {
        this(null, student, report, vdp, sanction);
    }

    public HistoryItem(Long id, Student student, Report report, Vdp vdp, Sanction sanction) {
        this.id = id;
        this.student = student;
        this.report = report;
        this.vdp = vdp;
        this.sanction = sanction;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
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
}
