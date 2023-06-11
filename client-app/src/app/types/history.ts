export interface HistoryItem {
    id: number;
    nivoNasilja: string;
    vdp: Vdp;
    sanction: Sanction;
    reportDate: number[];
}

export interface Vdp {
    value: string;
    description: string;
}

export interface Sanction {
    value: string;
    description: string;
}

export interface Report {
    studentId: number;
    nivoNasilja: string;
}