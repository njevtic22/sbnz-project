import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { User } from "src/app/types/user";
import { Subscription } from "rxjs";
import { constants } from "src/app/constants";
import { MatTable } from "@angular/material/table";
import { TeacherService } from "src/app/services/teacher.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { PaginatedResponse } from "src/app/types/paginated-response";

@Component({
    selector: "app-teachers-page",
    templateUrl: "./teachers-page.component.html",
    styleUrls: ["./teachers-page.component.scss"],
})
export class TeachersPageComponent implements OnInit, OnDestroy {
    teachers!: User[];
    private teachersSubscription: Subscription = new Subscription();

    private page: number = 0;
    private size: number = constants.MAX_SAFE_INTEGER_32;
    private sort: string = "id,asc";

    @ViewChild(MatTable) private table!: MatTable<any>;
    columnsToDisplay: string[] = [
        // "id",
        "name",
        "surname",
        "birthDate",
        "email",
        "username",
        // "role",
    ];

    constructor(
        private teacherService: TeacherService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.getTeachers();
    }

    ngOnDestroy(): void {
        this.teachersSubscription.unsubscribe();
    }

    getTeachers(): void {
        this.teachersSubscription.unsubscribe();
        this.teachersSubscription = this.teacherService
            .getTeachers(this.page, this.size, this.sort)
            .subscribe((result: PaginatedResponse<User>) => {
                this.teachers = result.data;
            }, this.errorHandler.handle);
    }
}
