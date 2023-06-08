import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { MatTable } from "@angular/material/table";
import { Router } from "@angular/router";
import { Subscription } from "rxjs";
import { ClassDialogComponent } from "src/app/components/class-dialog/class-dialog.component";
import { constants } from "src/app/constants";
import { ClassService } from "src/app/services/class.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { TeacherService } from "src/app/services/teacher.service";
import { Odeljenje, RequestOdeljenje } from "src/app/types/class";
import { ModalData, ModalResult } from "src/app/types/modal";
import { PaginatedResponse } from "src/app/types/paginated-response";
import { User } from "src/app/types/user";

@Component({
    selector: "app-classes-page",
    templateUrl: "./classes-page.component.html",
    styleUrls: ["./classes-page.component.scss"],
})
export class ClassesPageComponent implements OnInit, OnDestroy {
    teachers: User[] = [];
    private teachersSubscription: Subscription = new Subscription();

    classes: Odeljenje[] = [];
    private classSubscription: Subscription = new Subscription();

    private page: number = 0;
    private size: number = constants.MAX_SAFE_INTEGER_32;
    private sort: string = "id,asc";

    @ViewChild(MatTable) private table!: MatTable<any>;
    columnsToDisplay: string[] = [
        // "id",
        "naziv",
        "brojUcenika",
        "staresina",
        "studenti",
        "akcije",
    ];

    constructor(
        private router: Router,
        private dialog: MatDialog,
        private classService: ClassService,
        private teacherService: TeacherService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.getClasses();
        this.getNotStaresinaTeachers();
    }

    ngOnDestroy(): void {
        this.classSubscription.unsubscribe();
        this.teachersSubscription.unsubscribe();
    }

    getClasses(): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .getClasses(this.page, this.size, this.sort)
            .subscribe((response: PaginatedResponse<Odeljenje>) => {
                this.classes = response.data;
                this.table.renderRows();
            }, this.errorHandler.handle);
    }

    getNotStaresinaTeachers(): void {
        this.teachersSubscription.unsubscribe();
        this.teachersSubscription = this.teacherService
            .getNotStaresinaTeachers()
            .subscribe((teachers: User[]) => {
                this.teachers = teachers;
            }, this.errorHandler.handle);
    }

    openAddClassModal(): void {
        let classNames: string[] = [];
        this.classes.forEach((classElement: Odeljenje) => {
            classNames.push(classElement.naziv);
        });

        const data: ModalData<RequestOdeljenje> = {
            mainData: {
                naziv: "",
                staresinaId: -1,
            },
            additionalData: {
                teachers: this.teachers,
                classNames: classNames,
                title: "Novo odeljenje",
                buttonText: "Kreiraj",
            },
        };

        const dialogRef: MatDialogRef<ClassDialogComponent> = this.dialog.open(
            ClassDialogComponent,
            {
                data: data, // to share data by reference
                height: "360px",
                width: "400px",
            }
        );

        dialogRef
            .afterClosed()
            .subscribe((result: ModalResult<RequestOdeljenje>) => {
                if (result.success) {
                    this.addOdeljenje(result.data);
                }
            });
    }

    addOdeljenje(newOdeljenje: RequestOdeljenje): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .addClass(newOdeljenje)
            .subscribe(() => {
                this.getClasses();
                this.getNotStaresinaTeachers();
            }, this.errorHandler.handle);
    }

    deleteClass(odeljenje: Odeljenje): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .deleteClass(odeljenje.id)
            .subscribe(() => {
                this.getClasses();
                this.getNotStaresinaTeachers();
            }, this.errorHandler.handle);
    }

    openUpdateClassModal(odeljenje: Odeljenje): void {
        let classNames: string[] = [];
        this.classes.forEach((classElement: Odeljenje) => {
            if (classElement.naziv !== odeljenje.naziv) {
                classNames.push(classElement.naziv);
            }
        });

        let teachers: User[] = [odeljenje.staresina, ...this.teachers];

        const data: ModalData<RequestOdeljenje> = {
            mainData: {
                naziv: odeljenje.naziv,
                staresinaId: odeljenje.staresina.id as number,
            },
            additionalData: {
                teachers: teachers,
                classNames: classNames,
                title: "Promena podataka",
                buttonText: "Ažuriraj",
            },
        };

        const dialogRef: MatDialogRef<ClassDialogComponent> = this.dialog.open(
            ClassDialogComponent,
            {
                data: data, // to share data by reference
                height: "360px",
                width: "400px",
            }
        );

        dialogRef
            .afterClosed()
            .subscribe((result: ModalResult<RequestOdeljenje>) => {
                if (result.success) {
                    this.updateClass(odeljenje.id, result.data);
                }
            });
    }

    updateClass(classId: number, classToUpdate: RequestOdeljenje): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .updateClass(classId, classToUpdate)
            .subscribe((updatedClass: Odeljenje) => {
                this.getClasses();
                this.getNotStaresinaTeachers();
            }, this.errorHandler.handle);
    }

    redirectToStudentsPage(odeljenje: Odeljenje): void {
        sessionStorage.setItem("class", JSON.stringify(odeljenje));
        this.router.navigate([`classes/${odeljenje.id}/students`]);
    }
}
