import { Injectable } from "@angular/core";

@Injectable({
    providedIn: "root",
})
export class AuthenticationService {
    constructor() {}

    isAuthenticated(): boolean {
        return Boolean(sessionStorage.getItem("token"));
    }

    logout(): void {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("role");
    }
}
