import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ModalData } from "src/app/types/modal";
import { HistoryItem, Report } from "src/app/types/history";

@Component({
    selector: "app-report-answer-dialog",
    templateUrl: "./report-answer-dialog.component.html",
    styleUrls: ["./report-answer-dialog.component.scss"],
})
export class ReportAnswerDialogComponent implements OnInit {
    constructor(
        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<HistoryItem>, // this is reference to original data
        private dialogRef: MatDialogRef<ReportAnswerDialogComponent>
    ) {}

    ngOnInit(): void {
        //
    }

    closeCancel(): void {
        this.dialogRef.close({ success: false, data: null });
    }
}
