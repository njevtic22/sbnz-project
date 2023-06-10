package com.ftn.sbnz.service.service;


import com.ftn.sbnz.model.model.Odeljenje;

public interface OdeljenjeService extends CrudService<Odeljenje> {
    Odeljenje save(Odeljenje toSave);

    Odeljenje getForTeacher();

    boolean existsByNaziv(String naziv);
}
