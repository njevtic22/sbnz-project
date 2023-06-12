import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { HistoryItem, Report } from "src/app/types/history";
import { User } from "src/app/types/user";
import { Subscription } from "rxjs";
import { constants } from "src/app/constants";
import { MatTable } from "@angular/material/table";
import { StudentService } from "src/app/services/student.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { HistoryService } from "src/app/services/history.service";
import { UserService } from "src/app/services/user.service";
import { MatSnackBar, MatSnackBarConfig } from "@angular/material/snack-bar";
import { PaginatedResponse } from "src/app/types/paginated-response";
import {
    trigger,
    state,
    style,
    transition,
    animate,
} from "@angular/animations";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { ModalData, ModalResult } from "src/app/types/modal";
import { ReportStudentDialogComponent } from "src/app/components/report-student-dialog/report-student-dialog.component";
import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
    selector: "app-history-page",
    templateUrl: "./history-page.component.html",
    styleUrls: ["./history-page.component.scss"],
    animations: [
        trigger("detailExpand", [
            state("collapsed", style({ height: "0px", minHeight: "0" })),
            state("expanded", style({ height: "*" })),
            transition(
                "expanded <=> collapsed",
                animate("225ms cubic-bezier(0.4, 0.0, 0.2, 1)")
            ),
        ]),
    ],
})
export class HistoryPageComponent implements OnInit, OnDestroy {
    studentId: number | string = -1;
    student: User = {
        id: 0,
        name: "",
        surname: "",
        birthDate: [0, 0, 0],
        email: "",
        username: "",
        role: "ROLE_STUDENT",
        nivoSklonosti: "",
    };

    private studentSubscription: Subscription = new Subscription();

    history: HistoryItem[] = [];
    private historySubscription: Subscription = new Subscription();

    private page: number = 0;
    private size: number = constants.MAX_SAFE_INTEGER_32;
    private sort: string = "id,asc";

    @ViewChild(MatTable) private table!: MatTable<any>;
    columnsToDisplay: string[] = [
        // "id",
        "reportDate",
        "nivoNasilja",
        "tipNasilja",
        "vdp",
        "sanction",
    ];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private dialog: MatDialog,
        private snackbar: MatSnackBar,
        private userService: UserService,
        private studentService: StudentService,
        private authService: AuthenticationService,
        private historyService: HistoryService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        if (
            isNaN(this.route.snapshot.params["id"]) &&
            this.route.snapshot.params["id"] !== "authenticated"
        ) {
            this.router.navigate(["not-found"]);
            this.openSnackbar(
                `Failed to convert to number variable 'id' with value: '${this.route.snapshot.params["id"]}'`,
                "Close",
                {}
            );
        }

        if (this.route.snapshot.params["id"] === "authenticated") {
            this.getStudentProfile();
        } else {
            this.studentId = Number(this.route.snapshot.params["id"]);
            this.getStudent();
        }
    }

    ngOnDestroy(): void {
        this.studentSubscription.unsubscribe();
        this.historySubscription.unsubscribe();
    }

    isTeacher(): boolean {
        return this.authService.isTeacher();
    }

    getStudent(): void {
        this.studentSubscription.unsubscribe();
        this.studentSubscription = this.studentService
            .getStudent(this.studentId as number)
            .subscribe((student: User) => {
                this.student = student;
                this.studentId = this.student.id as number;
                this.getHistory();
            }, this.errorHandler.handle);
    }

    getStudentProfile(): void {
        this.studentSubscription.unsubscribe();
        this.studentSubscription = this.userService
            .getProfile()
            .subscribe((student: User) => {
                this.student = student;
                this.studentId = this.student.id as number;
                this.getHistory();
            }, this.errorHandler.handle);
    }

    getHistory(): void {
        this.historySubscription.unsubscribe();
        this.historySubscription = this.historyService
            .getHistory(
                this.student.id as number,
                this.page,
                this.size,
                this.sort
            )
            .subscribe((response: PaginatedResponse<HistoryItem>) => {
                this.history = response.data;
                this.history = this.history.map((item: HistoryItem) => ({
                    ...item,
                    isExpanded: false,
                }));
            }, this.errorHandler.handle);
    }

    openReportStudentModal(): void {
        const data: ModalData<Report> = {
            mainData: {
                studentId: this.studentId as number,
                nivoNasilja: "",
                tipNasilja: "",
                oblikNasilja: "",
                opis: "",
            },
            additionalData: {},
        };

        const dialogRef: MatDialogRef<ReportStudentDialogComponent> =
            this.dialog.open(ReportStudentDialogComponent, {
                data: data, // to share data by reference
                // height: "400px",
                // width: "400px",
            });

        dialogRef.afterClosed().subscribe((result: ModalResult<Report>) => {
            if (result.success) {
                this.reportStudent(result.data);
            }
        });
    }

    reportStudent(report: Report): void {
        this.historySubscription.unsubscribe();
        this.historySubscription = this.historyService
            .reportStudent(this.studentId as number, report)
            .subscribe((item: HistoryItem) => {
                console.log(item);
                this.getHistory();
            }, this.errorHandler.handle);
    }

    getImage(): string {
        return constants.userLogoImage;
    }

    private openSnackbar(
        message: string,
        action: string = "Zatvori",
        config: MatSnackBarConfig = { duration: 5 * 1000 }
    ) {
        this.snackbar.open(message, action, config);
    }
}
