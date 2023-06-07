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
import { ModalData } from "src/app/types/modal";
import { User } from "src/app/types/user";
import { validateLeadingTrailingWhitespace } from "src/app/util/validator/no-leading-trailing-whitespace";

@Component({
    selector: "app-teacher-dialog",
    templateUrl: "./teacher-dialog.component.html",
    styleUrls: ["./teacher-dialog.component.scss"],
})
export class TeacherDialogComponent implements OnInit {
    teacherForm!: FormGroup;

    private takenEmails: string[] = [];
    private takenUsernames: string[] = [];

    JSON = JSON;

    maxDate = new Date();

    constructor(
        private fb: FormBuilder,

        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<User>, // this is reference to original
        private dialogRef: MatDialogRef<TeacherDialogComponent>
    ) {}

    ngOnInit(): void {
        this.takenEmails = this.data.additionalData.takenEmails;
        this.takenUsernames = this.data.additionalData.takenUsernames;

        this.createTeacherForm();

        this.dialogRef.disableClose = true;
        this.dialogRef.backdropClick().subscribe(() => {
            this.closeCancel();
        });
    }

    createTeacherForm(): void {
        this.teacherForm = this.fb.group({
            id: [this.data.mainData.id, Validators.required],
            name: [
                this.data.mainData.name,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            surname: [
                this.data.mainData.surname,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            birthDate: [this.data.mainData.birthDate, Validators.required],
            email: [
                this.data.mainData.email,
                [
                    Validators.required,
                    Validators.email,
                    validateNotTaken(this.takenEmails),
                ],
            ],
            username: [
                this.data.mainData.username,
                [
                    Validators.required,
                    validateLeadingTrailingWhitespace(),
                    validateNotTaken(this.takenUsernames),
                ],
            ],
            role: [this.data.mainData.role, Validators.required],
            password: [
                this.data.mainData.password,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            repeatedPassword: [
                this.data.mainData.repeatedPassword,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
        });
    }

    closeSubmit(): void {
        const teacher: User = this.teacherForm.value;
        // teacher.birthDate = [1985, 10, 20];

        this.teacherForm.reset();
        this.dialogRef.close({ success: true, data: teacher });
    }

    closeCancel(): void {
        this.teacherForm.reset();
        this.dialogRef.close({ success: false, data: null });
    }
}

function validateNotTaken(takenValues: string[]): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const value: string = control.value;
        if (!value) {
            return null;
        }

        if (takenValues.includes(value)) {
            return {
                takenValue: {
                    valid: false,
                },
            };
        }
        return null;
    };
}
