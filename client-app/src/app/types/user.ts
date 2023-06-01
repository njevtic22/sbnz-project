export interface User {
    id?: number;
    name: string;
    surname: string;
    birthDate: number[];
    jmbg: string;
    email: string;
    username: string;
    role: string;
    nivoSklonosti?: string;
}

export interface UpdateUserResponse {
    updated: User;
    token: string;
}
