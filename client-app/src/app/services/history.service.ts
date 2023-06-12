import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environment/environment";
import { PaginatedResponse } from "../types/paginated-response";
import { HistoryItem, Report } from "../types/history";

const httpOptions = {
    headers: new HttpHeaders({
        "Content-Type": "application/json",
        // observe: "response",
    }),
};

@Injectable({
    providedIn: "root",
})
export class HistoryService {
    private hisotryUrl: string = `${environment.apiUrl}/students`;
    private histories: string = "histories";

    constructor(private http: HttpClient) {}

    reportStudent(studentId: number, report: Report): Observable<HistoryItem> {
        const url: string = `${this.hisotryUrl}/${studentId}/${this.histories}`;
        return this.http.post<HistoryItem>(url, report, httpOptions);
    }

    getHistory(
        studentId: number,
        page: number,
        size: number,
        sort: string
    ): Observable<PaginatedResponse<HistoryItem>> {
        const url: string = `${this.hisotryUrl}/${studentId}/${this.histories}?page=${page}&size=${size}&sort=${sort}`;
        return this.http.get<PaginatedResponse<HistoryItem>>(url);
    }
}
