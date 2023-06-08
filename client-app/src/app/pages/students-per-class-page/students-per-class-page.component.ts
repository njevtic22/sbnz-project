import { Component, OnInit, OnDestroy } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";

@Component({
    selector: "app-students-per-class-page",
    templateUrl: "./students-per-class-page.component.html",
    styleUrls: ["./students-per-class-page.component.scss"],
})
export class StudentsPerClassPageComponent implements OnInit, OnDestroy {
    classId: number = -1;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private snackbar: MatSnackBar
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
    }

    ngOnDestroy(): void {
        //
    }
}
