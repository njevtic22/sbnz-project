import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { Odeljenje, RequestOdeljenje } from "../types/class";
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
export class ClassService {
    private classUrl: string = `${environment.apiUrl}/odeljenja`;
    constructor(private http: HttpClient) {}

    getClasses(
        page: number,
        size: number,
        sort: string
    ): Observable<PaginatedResponse<Odeljenje>> {
        const url: string = `${this.classUrl}?page=${page}&size=${size}&sort=${sort}`;
        return this.http.get<PaginatedResponse<Odeljenje>>(url);
    }

    addClass(classToAdd: RequestOdeljenje): Observable<void> {
        return this.http.post<void>(this.classUrl, classToAdd, httpOptions);
    }

    deleteClass(classId: number): Observable<void> {
        const url: string = `${this.classUrl}/${classId}`;
        return this.http.delete<void>(url);
    }
}
