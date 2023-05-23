package com.ftn.sbnz.model.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {
    @Enumerated(EnumType.STRING)
    private NivoSklonostiKaNasilju nivoSklonosti;

    @OneToMany(mappedBy = "student")
    private List<Report> reports;

    @OneToMany(mappedBy = "student")
    private List<VdpForStudent> vdps;

    @OneToMany(mappedBy = "student")
    private List<SanctionForStudent> sanctions;

    public Student() { }

    public Student(String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String password, boolean archived, Role role, NivoSklonostiKaNasilju nivoSklonosti, List<Report> reports, List<VdpForStudent> vdps, List<SanctionForStudent> sanctions) {
        super(name, surname, birthDate, jmbg, email, username, password, archived, role);
        this.nivoSklonosti = nivoSklonosti;
        this.reports = reports;
        this.vdps = vdps;
        this.sanctions = sanctions;
    }

    public Student(Long id, String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String password, boolean archived, Role role, NivoSklonostiKaNasilju nivoSklonosti, List<Report> reports, List<VdpForStudent> vdps, List<SanctionForStudent> sanctions) {
        super(id, name, surname, birthDate, jmbg, email, username, password, archived, role);
        this.nivoSklonosti = nivoSklonosti;
        this.reports = reports;
        this.vdps = vdps;
        this.sanctions = sanctions;
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    public void addVdp(VdpForStudent vdp) {
        vdps.add(vdp);
    }

    public void addSanction(SanctionForStudent sanction) {
        sanctions.add(sanction);
    }

    public NivoSklonostiKaNasilju getNivoSklonosti() {
        return nivoSklonosti;
    }

    public void setNivoSklonosti(NivoSklonostiKaNasilju nivoSklonosti) {
        this.nivoSklonosti = nivoSklonosti;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<VdpForStudent> getVdps() {
        return vdps;
    }

    public void setVdps(List<VdpForStudent> vdps) {
        this.vdps = vdps;
    }

    public List<SanctionForStudent> getSanctions() {
        return sanctions;
    }

    public void setSanctions(List<SanctionForStudent> sanctions) {
        this.sanctions = sanctions;
    }
}
