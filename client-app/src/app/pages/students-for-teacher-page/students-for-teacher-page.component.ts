import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { Odeljenje } from "src/app/types/class";
import { User } from "src/app/types/user";
import { Subscription } from "rxjs";
import { constants } from "src/app/constants";
import { MatTable } from "@angular/material/table";
import { ClassService } from "src/app/services/class.service";
import { StudentService } from "src/app/services/student.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { PaginatedResponse } from "src/app/types/paginated-response";

@Component({
    selector: "app-students-for-teacher-page",
    templateUrl: "./students-for-teacher-page.component.html",
    styleUrls: ["./students-for-teacher-page.component.scss"],
})
export class StudentsForTeacherPageComponent implements OnInit, OnDestroy {
    class!: Odeljenje;
    private classSubscription: Subscription = new Subscription();

    students!: User[];
    private studentSubscription: Subscription = new Subscription();

    private page: number = 0;
    private size: number = constants.MAX_SAFE_INTEGER_32;
    private sort: string = "id,asc";

    JSON = JSON;

    @ViewChild(MatTable) private table!: MatTable<any>;
    columnsToDisplay: string[] = [
        // "id",
        "name",
        "surname",
        "birthDate",
        "email",
        "username",
        // "role",
        "nivoSklonosti",
        "history",
    ];

    constructor(
        private classService: ClassService,
        private studentService: StudentService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.getClassForTeacher();
        this.getStudentsForTeacher();
    }

    ngOnDestroy(): void {
        this.classSubscription.unsubscribe();
        this.studentSubscription.unsubscribe();
    }

    getClassForTeacher(): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .getClassForTeacher()
            .subscribe((odeljenje: Odeljenje) => {
                this.class = odeljenje;
            }, this.errorHandler.handle);
    }

    getStudentsForTeacher(): void {
        this.studentSubscription.unsubscribe();
        this.studentSubscription = this.studentService
            .getStudentsForTeacher(this.page, this.size, this.sort)
            .subscribe((response: PaginatedResponse<User>) => {
                this.students = response.data;
            }, this.errorHandler.handle);
    }
}
