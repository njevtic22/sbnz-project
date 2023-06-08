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
export class StudentService {
    private studentUrl: string = `${environment.apiUrl}/students`;

    constructor(private http: HttpClient) {}

    getStudentsForClass(
        classId: number,
        page: number,
        size: number,
        sort: string
    ): Observable<PaginatedResponse<User>> {
        const url: string = `${this.studentUrl}?page=${page}&size=${size}&sort=${sort}&classId=${classId}`;
        return this.http.get<PaginatedResponse<User>>(url);
    }
}
