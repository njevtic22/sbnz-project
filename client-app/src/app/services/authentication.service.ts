import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { LoginRequest, LoginResponse } from "../types/login";

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
}
