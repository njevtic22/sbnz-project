import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { HistoryItem } from "src/app/types/history";
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

@Component({
    selector: "app-history-page",
    templateUrl: "./history-page.component.html",
    styleUrls: ["./history-page.component.scss"],
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
        "nivoNasilja",
        "vdp",
        "sanction",
        "reportDate",
    ];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private snackbar: MatSnackBar,
        private userService: UserService,
        private studentService: StudentService,
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
            }, this.errorHandler.handle);
    }

    private openSnackbar(
        message: string,
        action: string = "Zatvori",
        config: MatSnackBarConfig = { duration: 5 * 1000 }
    ) {
        this.snackbar.open(message, action, config);
    }
}
