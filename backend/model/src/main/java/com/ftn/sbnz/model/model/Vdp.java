package com.ftn.sbnz.model.model;

public enum Vdp {
    SAVETODAVNI_RAD_NASTAVNIKA(
            "Savetodavni rad nastavnika sa učenikom i roditeljem."
    ),

    SAVETODAVNI_RAD_PPS(
            "Savetodavni rad pedagoško psihološke službe sa učenikom i roditeljem. Smanjenje ocene iz vladanja."
    ),

    POJACAN_RAD_NASTAVNIKA(
            "Pojačan vaspitni rad nastavnika sa učenikom i roditeljem. Smanjenje ocene iz vladanja."
    ),

    POJACAN_RAD_PPS(
            "Pojačan rad pedagoško psihološke službe sa učenikom i roditeljem. Smanjenje ocene iz vladanja."
    ),

    POJACAN_RAD_SS(
            "Pojačan rad pedagoško psihološke službe sa učenikom i roditeljem i uključivanje socijalne službe. Smanjenje ocene iz vladanja."
    ),

    POJACAN_RAD_MUP(
            "Pojačan rad pedagoško psihološke službe sa učenikom i roditeljem i uključivanje socijalne službe, tužilaštva i MUP-a."
    );

    private final String description;

    Vdp(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
