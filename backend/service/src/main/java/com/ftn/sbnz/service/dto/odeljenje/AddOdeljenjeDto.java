package com.ftn.sbnz.service.dto.odeljenje;

public class AddOdeljenjeDto extends RequestOdeljenjeDto {
    public AddOdeljenjeDto() {
        super();
    }

    public AddOdeljenjeDto(String naziv, Long staresinaId) {
        super(naziv, staresinaId);
    }
}
