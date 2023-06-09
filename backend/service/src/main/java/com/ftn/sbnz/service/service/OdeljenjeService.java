package com.ftn.sbnz.service.service;


import com.ftn.sbnz.model.model.Odeljenje;

public interface OdeljenjeService extends CrudService<Odeljenje> {
    boolean existsByNaziv(String naziv);

    Odeljenje save(Odeljenje toSave);
}
