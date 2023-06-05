import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { User } from "../types/user";

const httpOptions = {
    headers: new HttpHeaders({
        "Content-Type": "application/json",
        // observe: "response",
    }),
};

@Injectable({
    providedIn: "root",
})
export class TeacherService {
    private teacherUrl: string = `${environment.apiUrl}/teachers`;

    constructor(private http: HttpClient) {}

    getNotStaresinaTeachers(): Observable<User[]> {
        const url: string = `${this.teacherUrl}/not-staresina`;
        return this.http.get<User[]>(url);
    }
}
