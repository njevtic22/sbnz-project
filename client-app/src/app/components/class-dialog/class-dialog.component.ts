import { Component, Inject, OnInit } from "@angular/core";
import {
    AbstractControl,
    FormBuilder,
    FormGroup,
    ValidationErrors,
    ValidatorFn,
    Validators,
} from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { RequestOdeljenje } from "src/app/types/class";
import { ModalData } from "src/app/types/modal";
import { User } from "src/app/types/user";
import { validateLeadingTrailingWhitespace } from "src/app/util/validator/no-leading-trailing-whitespace";

@Component({
    selector: "app-class-dialog",
    templateUrl: "./class-dialog.component.html",
    styleUrls: ["./class-dialog.component.scss"],
})
export class ClassDialogComponent implements OnInit {
    classForm!: FormGroup;
    teachers: User[] = [];
    classNames: string[] = [];

    JSON = JSON;

    constructor(
        private fb: FormBuilder,

        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<RequestOdeljenje>, // this is reference to original data
        private dialogRef: MatDialogRef<ClassDialogComponent>
    ) {}

    ngOnInit(): void {
        this.teachers = this.data.additionalData.teachers;
        this.classNames = this.data.additionalData.classNames;

        this.createClassForm();

        this.dialogRef.disableClose = true;
        this.dialogRef.backdropClick().subscribe(() => {
            this.closeCancel();
        });
    }

    createClassForm(): void {
        this.classForm = this.fb.group({
            naziv: [
                this.data.mainData.naziv,
                [
                    Validators.required,
                    validateLeadingTrailingWhitespace(),
                    takenClassName(this.classNames),
                ],
            ],
            staresinaId: [
                null /*this.data.mainData.staresinaId*/,
                Validators.required,
            ],
        });
    }

    closeSubmit(): void {
        const reqOdeljenje: RequestOdeljenje = this.classForm.value;
        this.classForm.reset();
        this.dialogRef.close({ success: true, data: reqOdeljenje });
    }

    closeCancel(): void {
        this.classForm.reset();
        this.dialogRef.close({ success: false, data: null });
    }
}

function takenClassName(classNames: string[]): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const value: string = control.value;
        if (!value) {
            return null;
        }

        if (classNames.includes(value)) {
            return {
                takenClassName: {
                    valid: false,
                },
            };
        }
        return null;
    };
}
