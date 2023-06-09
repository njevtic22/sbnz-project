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
import { AddUserDialogComponent } from "src/app/components/user/add-user-dialog/add-user-dialog.component";
import { UserService } from "src/app/services/user.service";
import { TakenEmailsUsernames } from "src/app/types/taken-emails-usernames";
import { UpdateUserDialogComponent } from "src/app/components/user/update-user-dialog/update-user-dialog.component";
import { Moment } from "moment";
import * as moment from "moment";

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
        "akcije",
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
                startDate: new Date(1985, 6, 15),
                title: "Novi profesor",
                buttonText: "Kreiraj",
            },
        };

        const dialogRef: MatDialogRef<AddUserDialogComponent> =
            this.dialog.open(AddUserDialogComponent, {
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

    deleteTeacher(teacher: User): void {
        this.teacherSubscription.unsubscribe();
        this.teacherSubscription = this.teacherService
            .deleteTeacher(teacher.id as number)
            .subscribe(() => {
                this.getTeachers();
                // this.getTakenEmailsAndUsernames();
            }, this.errorHandler.handle);
    }

    openUpdateTeacherModal(teacher: User): void {
        let takenEmails: string[] = [];
        this.takenEmails.forEach((takenEmail: string) => {
            if (takenEmail !== teacher.email) {
                takenEmails.push(takenEmail);
            }
        });

        let takenUsernames: string[] = [];
        this.takenUsernames.forEach((takenUsername: string) => {
            if (takenUsername !== teacher.username) {
                takenUsernames.push(takenUsername);
            }
        });

        const birthDateArray: number[] = teacher.birthDate as number[];
        const birthDateStr: string =
            birthDateArray[0] +
            "-" +
            birthDateArray[1] +
            "-" +
            birthDateArray[2];
        const birthDateMoment: Moment = moment(birthDateStr, "YYYY-MM-DD");

        const data: ModalData<User> = {
            mainData: {
                id: teacher.id,
                name: teacher.name,
                surname: teacher.surname,
                birthDate: birthDateMoment,
                email: teacher.email,
                username: teacher.username,
                role: teacher.role,
            },
            additionalData: {
                takenEmails: takenEmails,
                takenUsernames: takenUsernames,
                title: "Promena podataka",
                buttonText: "Ažuriraj",
            },
        };

        const dialogRef: MatDialogRef<UpdateUserDialogComponent> =
            this.dialog.open(UpdateUserDialogComponent, {
                data: data, // to share data by reference
                // height: "400px",
                // width: "400px",
            });

        dialogRef.afterClosed().subscribe((result: ModalResult<User>) => {
            if (result.success) {
                this.updateTeacher(result.data);
            }
        });
    }

    updateTeacher(changes: User): void {
        this.teacherSubscription.unsubscribe();
        this.teacherSubscription = this.teacherService
            .updateTeacher(changes)
            .subscribe(() => {
                this.getTeachers();
                this.getTakenEmailsAndUsernames();
            }, this.errorHandler.handle);
    }
}
