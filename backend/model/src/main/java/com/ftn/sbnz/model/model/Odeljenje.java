package com.ftn.sbnz.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "odeljenja")
public class Odeljenje {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odeljenje_generator")
    @SequenceGenerator(name = "odeljenje_generator", sequenceName = "odeljenje_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @ManyToOne
//    @OneToOne(optional = true) is same as many to one IN DATABASE
    private Teacher staresina;

    @OneToMany
    private List<Student> ucenici;

    @Column(nullable = false)
    private boolean archived;

    public Odeljenje() { }

    public Odeljenje(String naziv, Teacher staresina, List<Student> ucenici, boolean archived) {
        this(null, naziv, staresina, ucenici, archived);
    }

    public Odeljenje(Long id, String naziv, Teacher staresina, List<Student> ucenici, boolean archived) {
        this.id = id;
        this.naziv = naziv;
        this.staresina = staresina;
        this.ucenici = ucenici;
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Odeljenje)) return false;
        Odeljenje odeljenje = (Odeljenje) o;
        return Objects.equals(id, odeljenje.id) && naziv.equals(odeljenje.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    public void addStudent(Student student) {
        this.ucenici.add(student);
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Teacher getStaresina() {
        return staresina;
    }

    public void setStaresina(Teacher staresina) {
        this.staresina = staresina;
    }

    public List<Student> getUcenici() {
        return ucenici;
    }

    public void setUcenici(List<Student> ucenici) {
        this.ucenici = ucenici;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
