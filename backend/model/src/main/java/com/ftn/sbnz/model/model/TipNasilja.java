package com.ftn.sbnz.model.model;

public enum TipNasilja {
    FIZICKO("Fizičko"),
    VERBALNO("Verbalno"),
    SOCIJALNO("Socijalno"),
    SEKSUALNO("Seksualno"),
    INFORMATICKO("Informatičko");

    private final String description;

    TipNasilja(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
