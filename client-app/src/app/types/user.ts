export interface User {
    id?: number;
    name: string;
    surname: string;
    birthDate: number[];
    email: string;
    username: string;
    role: string;
    nivoSklonosti?: string;
    password?: string;
    repeatedPassword?: string;
}

export interface UpdateUserResponse {
    updated: User;
    token: string;
}
