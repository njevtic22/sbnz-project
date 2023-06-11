package com.ftn.sbnz.model.model;

public enum NivoNasilja {
    PRVI("Prvi"),
    DRUGI("Drugi"),
    TRECI("Treći");

    private final String description;

    NivoNasilja(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
