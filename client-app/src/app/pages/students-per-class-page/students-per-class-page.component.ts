import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { StudentService } from "src/app/services/student.service";
import { Subscription } from "rxjs";
import { User } from "src/app/types/user";
import { constants } from "src/app/constants";
import { PaginatedResponse } from "src/app/types/paginated-response";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { Odeljenje } from "src/app/types/class";
import { MatTable } from "@angular/material/table";
import { ModalData, ModalResult } from "src/app/types/modal";
import { UserService } from "src/app/services/user.service";
import { TakenEmailsUsernames } from "src/app/types/taken-emails-usernames";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { AddUserDialogComponent } from "src/app/components/user/add-user-dialog/add-user-dialog.component";

@Component({
    selector: "app-students-per-class-page",
    templateUrl: "./students-per-class-page.component.html",
    styleUrls: ["./students-per-class-page.component.scss"],
})
export class StudentsPerClassPageComponent implements OnInit, OnDestroy {
    classId: number = -1;
    class!: Odeljenje;

    students!: User[];
    private studentSubscription: Subscription = new Subscription();

    private takenEmails: string[] = [];
    private takenUsernames: string[] = [];
    private takenSubscription: Subscription = new Subscription();

    private page: number = 0;
    private size: number = constants.MAX_SAFE_INTEGER_32;
    private sort: string = "id,desc";

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
    ];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private dialog: MatDialog,
        private snackbar: MatSnackBar,
        private studentService: StudentService,
        private userService: UserService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        if (isNaN(this.route.snapshot.params["id"])) {
            this.router.navigate(["not-found"]);
            this.snackbar.open(
                `Failed to convert to number variable 'id' with value: '${this.route.snapshot.params["id"]}'`,
                "Close"
            );
        }

        this.classId = Number(this.route.snapshot.params["id"]);
        this.class = JSON.parse(sessionStorage.getItem("class") as string);

        this.getStudentsForClass();
        this.getTakenEmailsAndUsernames();
    }

    ngOnDestroy(): void {
        sessionStorage.removeItem("class");
        this.studentSubscription.unsubscribe();
        this.takenSubscription.unsubscribe();
    }

    getStudentsForClass(): void {
        this.studentSubscription.unsubscribe();
        this.studentSubscription = this.studentService
            .getStudentsForClass(this.classId, this.page, this.size, this.sort)
            .subscribe((response: PaginatedResponse<User>) => {
                this.students = response.data;
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

    openAddStudentModal(): void {
        const data: ModalData<User> = {
            mainData: {
                id: -1,
                name: "",
                surname: "",
                birthDate: "",
                email: "",
                username: "",
                role: "ROLE_STUDENT",
                password: "",
                repeatedPassword: "",
            },
            additionalData: {
                takenEmails: this.takenEmails,
                takenUsernames: this.takenUsernames,
                startDate: new Date(2007, 6, 15),
                title: "Novi učenik",
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
                this.addStudent(result.data);
            }
        });
    }

    addStudent(newStudent: User): void {
        this.studentSubscription.unsubscribe();
        this.studentSubscription = this.studentService
            .addStudent(this.classId, newStudent)
            .subscribe(() => {
                this.getStudentsForClass();
                this.getTakenEmailsAndUsernames();
            }, this.errorHandler.handle);
    }
}
