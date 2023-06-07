import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { User } from "src/app/types/user";
import { Subscription } from "rxjs";
import { constants } from "src/app/constants";
import { MatTable } from "@angular/material/table";
import { TeacherService } from "src/app/services/teacher.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { PaginatedResponse } from "src/app/types/paginated-response";
import { ModalData, ModalResult } from "src/app/types/modal";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { TeacherDialogComponent } from "src/app/components/teacher-dialog/teacher-dialog.component";
import { UserService } from "src/app/services/user.service";
import { TakenEmailsUsernames } from "src/app/types/taken-emails-usernames";

@Component({
    selector: "app-teachers-page",
    templateUrl: "./teachers-page.component.html",
    styleUrls: ["./teachers-page.component.scss"],
})
export class TeachersPageComponent implements OnInit, OnDestroy {
    teachers: User[] = [];
    private teacherSubscription: Subscription = new Subscription();

    private takenEmails: string[] = [];
    private takenUsernames: string[] = [];
    private takenSubscription: Subscription = new Subscription();

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
        private dialog: MatDialog,
        private teacherService: TeacherService,
        private userService: UserService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.getTeachers();
        this.getTakenEmailsAndUsernames();
    }

    ngOnDestroy(): void {
        this.teacherSubscription.unsubscribe();
        this.takenSubscription.unsubscribe();
    }

    getTeachers(): void {
        this.teacherSubscription.unsubscribe();
        this.teacherSubscription = this.teacherService
            .getTeachers(this.page, this.size, this.sort)
            .subscribe((result: PaginatedResponse<User>) => {
                this.teachers = result.data;
                this.table.renderRows();
            }, this.errorHandler.handle);
    }

    getTakenEmailsAndUsernames(): void {
        this.takenSubscription.unsubscribe();
        this.takenSubscription = this.userService
            .getTakenEmailsAndUsernames()
            .subscribe((taken: TakenEmailsUsernames) => {
                this.takenEmails = taken.emails;
                this.takenUsernames = taken.usernames;
            }, this.errorHandler.handle);
    }

    openAddTeacherModal(): void {
        const data: ModalData<User> = {
            mainData: {
                id: -1,
                name: "",
                surname: "",
                birthDate: "",
                email: "",
                username: "",
                role: "ROLE_TEACHER",
                password: "",
                repeatedPassword: "",
            },
            additionalData: {
                takenEmails: this.takenEmails,
                takenUsernames: this.takenUsernames,
                buttonText: "Kreiraj",
            },
        };

        const dialogRef: MatDialogRef<TeacherDialogComponent> =
            this.dialog.open(TeacherDialogComponent, {
                data: data, // to share data by reference
                // height: "400px",
                // width: "400px",
            });

        dialogRef.afterClosed().subscribe((result: ModalResult<User>) => {
            if (result.success) {
                this.addTeacher(result.data);
            }
        });
    }

    addTeacher(newTeacher: User): void {
        this.teacherSubscription.unsubscribe();
        this.teacherSubscription = this.teacherService
            .addTeacher(newTeacher)
            .subscribe(() => {
                this.getTeachers();
                this.getTakenEmailsAndUsernames();
            }, this.errorHandler.handle);
    }
}
