package com.ftn.sbnz.service.dto.odeljenje;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public abstract class RequestOdeljenjeDto {
    @NotBlank(message = "Naziv must not be blank.")
    private String naziv;

    @NotNull(message = "Teacher id must not be null.")
    @Positive(message = "Teacher id must be positive long.")
    private Long staresinaId;

    public RequestOdeljenjeDto() { }

    public RequestOdeljenjeDto(String naziv, Long staresinaId) {
        this.naziv = naziv;
        this.staresinaId = staresinaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public Long getStaresinaId() {
        return staresinaId;
    }
}
