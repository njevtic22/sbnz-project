package com.ftn.sbnz.model.model;

public enum OblikNasilja {
    // FIZICKO - NIVO 1
    UDARANJE_CVRGA("Udaranje čvrga"),
    GURANJE("Guranje"),
    STIPANJE("Štipanje"),
    GADJANJE("Gađanje"),
    SAPLITANJE("Saplitanje"),
    SUTIRANJE("Šutiranje"),
    PRLJANJE("Prljanje"),

    // VERBALNO - NIVO 1
    OMALOVAZAVANJE("Omalovažavanje"),
    OGOVARANJE("Ogovaranje"),
    VREDJANJE("Vredjanje"),
    RUGANJE("Ruganje"),
    NAZIVANJE_POGRDNIM_IMENOM("Nazivanje pogrdnim imenom"),
    PSOVANJE("Psovanje"),
    ETIKETIRANJE("Etiketikranje"),

    //SOCIJALNO - NIVO 1
    ODBACIVANJE_IZ_GRUPE("Odbacivanje iz grupe"),
    ISMEVANJE("Ismevanje"),
    SIRENJE_GLASINA("Širenje glasina"),

    // SEKSULANO - NIVO 1
    DOBACIVANJE("Dobacivanje"),
    S_PSOVANJE("Psovanje"),
    KOMENTARI("Komentari"),
    S_ETIKETIRANJE("Etiketiranje"),
    SEKSUALNA_GESTIKULACIJA("Seskualna gestikulacija"),

    // INFORMATICKO - NIVO 1
    UZNEMIRAVAJUCI_POZIVI("Uznemiravajući pozivi"),
    UZNEMIRAVAJUCE_PORUKE("Uznemiravajuće poruke"),

    //////////////////////////////
    // FIZICKO - NIVO 2
    SAMARANJE("Šamaranje"),
    UDARANJE("Udaranje"),
    CEPANJE_ODELA("Cepanje odela"),
    GAZENJE("Gaženje"),
    PLJUVANJE("Pljuvanje"),
    UNISTAVAJNE_IMOVINE("Uništavanje imovine"),
    IZMICANJE_STOLICE("Izmicanje stolice"),
    CUPANJE_ZA_KOSU("Čupanje za uši i kosu"),

    // VERBALNO - NIVO 2
    UCENJIVANJE("Ucenjivanje"),
    PRETNJE("Pretnje"),
    NEPRAVEDNO_KAZNJAVANJE("Nepravedno kažnjavanje"),
    ZABRANA_KOMUNIKACIJE("Zabrana komunikacije"),

    // SOCIJALNO - NIVO 2
    SPLETKARENJE("Spletkarenje"),
    IGNORISANJE("Ignorisanje"),
    MANIPULISANJE("Manipulisanje"),
    ISKORISCAVANJE("Iskoriscavanje"),

    // SEKSUALNO - NIVO 2
    DODIRIVANJE("Dodirivanje"),
    POKAZIVANJE_PORNOGRAFSKOG_MATERIJALA("Pokazivanje pornografskog materijala"),
    POKAZIVANJE_INTIMNIH_DELOVA_TELA("Pokazivanje intimnih delova tela"),
    SVLACENJE("Svlačenje"),

    // INFORMACIONO - NIVO 2
    SNIMANJE_I_SIRENJE_VIDEO_ZAPISA("Snimanje i širenje video zapisa"),
    ZLOUPOTREBA_DRUSTVENIH_MREZA("Zloupotreba društvenih mreža"),


    //////////////////////////////
    // FIZICKO - NIVO 3
    TUCA("Tuča"),
    DAVLJENJE("Davljenje"),
    USKRACIVANJE_HRANE("Uskraćivanje hrane"),
    NAPAD_ORUZJEM("Napad oružjem"),
    IZLAGANJE_NISKIM_I_VISOKIM_TEMPERATURAMA("Izlaganje niskim i visokim temperaturama"),

    // VERBALNO - NIVO 3
    ZASTRASIVANJE("Zastrašivanje"),
    UCENJIVANJE_UZ_OZBILJNU_PRETNJU("Ucenjivanje uz ozbiljnu pretnju"),
    NAVODJENJE_NA_KORISCENJE_PSIHOAKTIVNIH_SUPSTANCI("Navođenje na korisćenje psihoaktivnih supstanci"),

    // SOCIJALNO - NIVO 3
    IZNUDJIVANJE("Iznuđivanje novca ili stvari"),
    MALTRETIRANJE_GRUPE_PREMA_POJEDINCU("Maltretiranje grupe prema pojedincu"),
    ORGANIZOVANJE_ZATVORENIH_GRUPA("Organizovanje zatvorenih gupra (klanova) koji ima za posledicu povređivanje drugih"),

    // SEKSUALNO - NIVO 3
    PODVODJENJE("Podvođenje"),
    SILOVANJE("Silovanje"),

    // INFORMACIONO - NIVO 3
    SNIMANJE_I_SIRENJE_VIDEO_ZAPISA_SA_NASILNIM_SCENAMA("Snimanje i širenje video zapisa sa nasilnim scenama"),
    DECIJA_PORNOGRAFIJA("Dečija pornografija");


    private final String description;

    OblikNasilja(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
