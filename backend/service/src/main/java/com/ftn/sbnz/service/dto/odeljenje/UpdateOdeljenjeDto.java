package com.ftn.sbnz.service.dto.odeljenje;

public class UpdateOdeljenjeDto extends RequestOdeljenjeDto {
    public UpdateOdeljenjeDto() {
        super();
    }

    public UpdateOdeljenjeDto(String naziv, Long staresinaId) {
        super(naziv, staresinaId);
    }
}
