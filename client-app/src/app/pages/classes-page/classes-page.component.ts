import { Component, OnInit, OnDestroy, ViewChild } from "@angular/core";
import { MatTable } from "@angular/material/table";
import { Subscription } from "rxjs";
import { constants } from "src/app/constants";
import { ClassService } from "src/app/services/class.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { Odeljenje } from "src/app/types/class";
import { PaginatedResponse } from "src/app/types/paginated-response";

@Component({
    selector: "app-classes-page",
    templateUrl: "./classes-page.component.html",
    styleUrls: ["./classes-page.component.scss"],
})
export class ClassesPageComponent implements OnInit, OnDestroy {
    classes!: Odeljenje[];
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
    ];

    constructor(
        private classService: ClassService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.getClasses();
    }

    ngOnDestroy(): void {
        this.classSubscription.unsubscribe();
    }

    getClasses(): void {
        this.classSubscription.unsubscribe();
        this.classSubscription = this.classService
            .getClasses(this.page, this.size, this.sort)
            .subscribe((response: PaginatedResponse<Odeljenje>) => {
                this.classes = response.data;
            }, this.errorHandler.handle);
    }
}
