import { User } from "./user";

export interface Odeljenje {
    id: number;
    naziv: string;
    brojUcenika: number;
    staresina: User;
}

export interface RequestOdeljenje {
    naziv: string;
    staresinaId: number;
}
