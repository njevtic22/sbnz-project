export enum NivoNasilja {
    PRVI = "Prvi",
    DRUGI = "Drugi",
    TRECI = "Treci",
}

export enum TipNasilja {
    FIZICKO = "Fizičko",
    VERBALNO = "Verbalno",
    SOCIJALNO = "Socijalno",
    SEKSUALNO = "Seksualno",
    INFORMATICKO = "Informatičko",
}

export const StructuredViolences: { [key: string]: any } = {
    PRVI: {
        FIZICKO: {
            UDARANJE_CVRGA: "Udaranje čvrga",
            GURANJE: "Guranje",
            STIPANJE: "Štipanje",
            GADJANJE: "Gađanje",
            SAPLITANJE: "Saplitanje",
            SUTIRANJE: "Šutiranje",
            PRLJANJE: "Prljanje",
        },
        VERBALNO: {
            OMALOVAZAVANJE: "Omalovažavanje",
            OGOVARANJE: "Ogovaranje",
            VREDJANJE: "Vredjanje",
            RUGANJE: "Ruganje",
            NAZIVANJE_POGRDNIM_IMENOM: "Nazivanje pogrdnim imenom",
            PSOVANJE: "Psovanje",
            ETIKETIRANJE: "Etiketikranje",
        },
        SOCIJALNO: {
            ODBACIVANJE_IZ_GRUPE: "Odbacivanje iz grupe",
            ISMEVANJE: "Ismevanje",
            SIRENJE_GLASINA: "Širenje glasina",
        },
        SEKSUALNO: {
            DOBACIVANJE: "Dobacivanje",
            S_PSOVANJE: "Psovanje",
            KOMENTARI: "Komentari",
            S_ETIKETIRANJE: "Etiketiranje",
            SEKSUALNA_GESTIKULACIJA: "Seskualna gestikulacija",
        },
        INFORMATICKO: {
            UZNEMIRAVAJUCI_POZIVI: "Uznemiravajući pozivi",
            UZNEMIRAVAJUCE_PORUKE: "Uznemiravajuće poruke",
        },
    },
    DRUGI: {
        FIZICKO: {
            SAMARANJE: "Šamaranje",
            UDARANJE: "Udaranje",
            CEPANJE_ODELA: "Cepanje odela",
            GAZENJE: "Gaženje",
            PLJUVANJE: "Pljuvanje",
            UNISTAVAJNE_IMOVINE: "Uništavanje imovine",
            IZMICANJE_STOLICE: "Izmicanje stolice",
            CUPANJE_ZA_KOSU: "Čupanje za uši i kosu",
        },
        VERBALNO: {
            UCENJIVANJE: "Ucenjivanje",
            PRETNJE: "Pretnje",
            NEPRAVEDNO_KAZNJAVANJE: "Nepravedno kažnjavanje",
            ZABRANA_KOMUNIKACIJE: "Zabrana komunikacije",
        },
        SOCIJALNO: {
            SPLETKARENJE: "Spletkarenje",
            IGNORISANJE: "Ignorisanje",
            MANIPULISANJE: "Manipulisanje",
            ISKORISCAVANJE: "Iskoriscavanje",
        },
        SEKSUALNO: {
            DODIRIVANJE: "Dodirivanje",
            POKAZIVANJE_PORNOGRAFSKOG_MATERIJALA:
                "Pokazivanje pornografskog materijala",
            POKAZIVANJE_INTIMNIH_DELOVA_TELA:
                "Pokazivanje intimnih delova tela",
            SVLACENJE: "Svlačenje",
        },
        INFORMATICKO: {
            SNIMANJE_I_SIRENJE_VIDEO_ZAPISA: "Snimanje i širenje video zapisa",
            ZLOUPOTREBA_DRUSTVENIH_MREZA: "Zloupotreba društvenih mreža",
        },
    },
    TRECI: {
        FIZICKO: {
            TUCA: "Tuča",
            DAVLJENJE: "Davljenje",
            USKRACIVANJE_HRANE: "Uskraćivanje hrane",
            NAPAD_ORUZJEM: "Napad oružjem",
            IZLAGANJE_NISKIM_I_VISOKIM_TEMPERATURAMA:
                "Izlaganje niskim i visokim temperaturama",
        },
        VERBALNO: {
            ZASTRASIVANJE: "Zastrašivanje",
            UCENJIVANJE_UZ_OZBILJNU_PRETNJU: "Ucenjivanje uz ozbiljnu pretnju",
            NAVODJENJE_NA_KORISCENJE_PSIHOAKTIVNIH_SUPSTANCI:
                "Navođenje na korisćenje psihoaktivnih supstanci",
        },
        SOCIJALNO: {
            IZNUDJIVANJE: "Iznuđivanje novca ili stvari",
            MALTRETIRANJE_GRUPE_PREMA_POJEDINCU:
                "Maltretiranje grupe prema pojedincu",
            ORGANIZOVANJE_ZATVORENIH_GRUPA:
                "Organizovanje zatvorenih gupra : klanova) koji ima za posledicu povređivanje drugih",
        },
        SEKSUALNO: {
            PODVODJENJE: "Podvođenje",
            SILOVANJE: "Silovanje",
        },
        INFORMATICKO: {
            SNIMANJE_I_SIRENJE_VIDEO_ZAPISA_SA_NASILNIM_SCENAMA:
                "Snimanje i širenje video zapisa sa nasilnim scenama",
            DECIJA_PORNOGRAFIJA: "Dečija pornografija",
        },
    },
};
