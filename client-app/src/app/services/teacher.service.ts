import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { User } from "../types/user";
import { PaginatedResponse } from "../types/paginated-response";

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

    addTeacher(newTeacher: User): Observable<void> {
        return this.http.post<void>(this.teacherUrl, newTeacher, httpOptions);
    }

    getTeachers(
        page: number,
        size: number,
        sort: string
    ): Observable<PaginatedResponse<User>> {
        const url: string = `${this.teacherUrl}?page=${page}&size=${size}&sort=${sort}`;
        return this.http.get<PaginatedResponse<User>>(url);
    }

    getNotStaresinaTeachers(): Observable<User[]> {
        const url: string = `${this.teacherUrl}/not-staresina`;
        return this.http.get<User[]>(url);
    }
}
