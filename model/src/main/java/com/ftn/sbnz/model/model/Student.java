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
    private List<HistoryItem> history;

    public Student() { }

    public Student(String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String password, boolean archived, Role role, NivoSklonostiKaNasilju nivoSklonosti, List<HistoryItem> history) {
        this(null, name, surname, birthDate, jmbg, email, username, password, archived, role, nivoSklonosti, history);
    }

    public Student(Long id, String name, String surname, LocalDate birthDate, String jmbg, String email, String username, String password, boolean archived, Role role, NivoSklonostiKaNasilju nivoSklonosti, List<HistoryItem> history) {
        super(id, name, surname, birthDate, jmbg, email, username, password, archived, role);
        this.nivoSklonosti = nivoSklonosti;
        this.history = history;
    }

    public void addToHistory(HistoryItem item) {
        history.add(item);
    }

    public NivoSklonostiKaNasilju getNivoSklonosti() {
        return nivoSklonosti;
    }

    public void setNivoSklonosti(NivoSklonostiKaNasilju nivoSklonosti) {
        this.nivoSklonosti = nivoSklonosti;
    }

    public List<HistoryItem> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryItem> history) {
        this.history = history;
    }
}
