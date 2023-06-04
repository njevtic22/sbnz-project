package com.ftn.sbnz.service.dto.odeljenje;

import com.ftn.sbnz.service.dto.teacher.TeacherViewDto;

public class OdeljenjeViewDto {
    private final Long id;
    private final String naziv;
    private final Long brojUcenika;
    private final TeacherViewDto staresina;

    public OdeljenjeViewDto(Long id, String naziv, Long brojUcenika, TeacherViewDto staresina) {
        this.id = id;
        this.naziv = naziv;
        this.brojUcenika = brojUcenika;
        this.staresina = staresina;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Long getBrojUcenika() {
        return brojUcenika;
    }

    public TeacherViewDto getStaresina() {
        return staresina;
    }
}
