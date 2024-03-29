import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { LoginRequest, LoginResponse } from "../types/login";
import { Role } from "../types/role";

const httpOptions = {
    headers: new HttpHeaders({
        "Content-Type": "application/json",
    }),
};

@Injectable({
    providedIn: "root",
})
export class AuthenticationService {
    private authUrl: string = `${environment.apiUrl}/auth`;

    constructor(private http: HttpClient) {}

    login(loginData: LoginRequest): Observable<LoginResponse> {
        const url: string = `${this.authUrl}/login`;
        return this.http.post<LoginResponse>(url, loginData, httpOptions);
    }

    logout(): void {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("role");
    }

    isAuthenticated(): boolean {
        return Boolean(sessionStorage.getItem("token"));
    }

    isAdmin(): boolean {
        return sessionStorage.getItem("role") === Role[Role.ROLE_ADMIN];
    }

    isTeacher(): boolean {
        return sessionStorage.getItem("role") === Role[Role.ROLE_TEACHER];
    }

    isStudent(): boolean {
        return sessionStorage.getItem("role") === Role[Role.ROLE_STUDENT];
    }

    getRole(): string {
        const role: string | null = sessionStorage.getItem("role");
        if (!role) {
            return Role[Role.ROLE_ANONYMOUS];
        }
        return role;
    }
}
