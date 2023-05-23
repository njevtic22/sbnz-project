package com.ftn.sbnz.model.model;

public enum Sanction {
    WARNING("Opomena"),
    SUSPENSION_1("Suspenzija na 1 dan"),
    SUSPENSION_3("Suspenzija na 3 dana"),
    SUSPENSION_7("Suspenzija na 7 dana"),
    TRANSFER("Ispisivanje učenika iz škole i upisivanje u drugu školu");

    private final String description;

    Sanction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
