import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ModalData } from "src/app/types/modal";
import { Report } from "src/app/types/history";
import {
    getNivoNasiljaKeyValuePairs,
    getTipNasiljaKeyValuePairs,
    getOblikNasiljaKeyValuePairs,
} from "src/app/types/violence-types-util";
import { KeyValuePair } from "src/app/types/pair";

@Component({
    selector: "app-report-student-dialog",
    templateUrl: "./report-student-dialog.component.html",
    styleUrls: ["./report-student-dialog.component.scss"],
})
export class ReportStudentDialogComponent implements OnInit {
    reportForm!: FormGroup;

    nivoNasiljaKeyValues: KeyValuePair<string, string>[] =
        getNivoNasiljaKeyValuePairs();

    tipNasiljaKeyValues: KeyValuePair<string, string>[] =
        getTipNasiljaKeyValuePairs();

    oblikNasiljaKeyValues: KeyValuePair<string, string>[] = [];
    // getOblikNasiljaKeyValuePairs("PRVI", "FIZICKO");

    constructor(
        private fb: FormBuilder,

        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<Report>, // this is reference to original data
        private dialogRef: MatDialogRef<ReportStudentDialogComponent>
    ) {}

    ngOnInit(): void {
        this.createReportForm();

        this.dialogRef.disableClose = true;
        this.dialogRef.backdropClick().subscribe(() => {
            this.closeCancel();
        });
    }

    createReportForm(): void {
        this.reportForm = this.fb.group({
            studentId: [this.data.mainData.studentId, Validators.required],
            nivoNasilja: ["", Validators.required],
            tipNasilja: ["", Validators.required],
            oblikNasilja: ["", Validators.required],
            opis: ["", Validators.required],
        });
    }

    changeObliciNasilja(): void {
        const nivo: string = this.reportForm.controls["nivoNasilja"].value;
        const tip: string = this.reportForm.controls["tipNasilja"].value;

        if (!nivo || !tip) {
            return;
        }

        this.oblikNasiljaKeyValues = getOblikNasiljaKeyValuePairs(nivo, tip);
        this.reportForm.controls["oblikNasilja"].setValue("");
    }

    closeSubmit(): void {
        const report: Report = this.reportForm.value;
        this.reportForm.reset();
        this.dialogRef.close({ success: true, data: report });
    }

    closeCancel(): void {
        this.reportForm.reset();
        this.dialogRef.close({ success: false, data: null });
    }
}
