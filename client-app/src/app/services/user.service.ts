import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../types/user";
import { AuthenticationService } from "./authentication.service";
import { environment } from "src/environment/environment";

const httpOptions = {
    headers: new HttpHeaders({
        "Content-Type": "application/json",
        // observe: "response",
    }),
};

@Injectable({
    providedIn: "root",
})
export class UserService {
    private roleUrls: any = {
        ROLE_ADMIN: "/admins",
        ROLE_TEACHER: "/teachers",
        ROLE_STUDENT: "/students",
    };

    constructor(
        private http: HttpClient,
        private authService: AuthenticationService
    ) {}

    getProfile(): Observable<User> {
        const role: string = this.authService.getRole();
        const urlSufix: string = this.roleUrls[role];
        const url: string = environment.apiUrl + urlSufix + "/profile";

        return this.http.get<User>(url);
    }
}
