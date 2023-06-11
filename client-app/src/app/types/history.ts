export interface HistoryItem {
    id: number;
    nivoNasilja: ValueDescriptionPair;
    tipNasilja: ValueDescriptionPair;
    oblikNasilja: ValueDescriptionPair;
    opis: string;
    vdp: ValueDescriptionPair;
    sanction: ValueDescriptionPair;
    reportDate: number[];
}

export interface ValueDescriptionPair {
    value: string;
    description: string;
}

export interface Report {
    studentId: number;
    nivoNasilja: string;
    tipNasilja: string;
    oblikNasilja: string;
    opis: string;
}
