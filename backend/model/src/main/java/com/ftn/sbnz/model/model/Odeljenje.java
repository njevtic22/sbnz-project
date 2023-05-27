package com.ftn.sbnz.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @OneToOne
    private Teacher staresina;

    @OneToMany
    private List<Student> ucenici;

    public Odeljenje() { }

    public Odeljenje(String naziv, Teacher staresina, List<Student> ucenici) {
        this(null, naziv, staresina, ucenici);
    }

    public Odeljenje(Long id, String naziv, Teacher staresina, List<Student> ucenici) {
        this.id = id;
        this.naziv = naziv;
        this.staresina = staresina;
        this.ucenici = ucenici;
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
}