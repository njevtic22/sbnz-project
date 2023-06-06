import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
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

    constructor(
        private fb: FormBuilder,

        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<User>, // this is reference to original
        private dialogRef: MatDialogRef<TeacherDialogComponent>
    ) {}

    ngOnInit(): void {
        this.createTeacherForm();

        this.dialogRef.disableClose = true;
        this.dialogRef.backdropClick().subscribe(() => {
            this.closeCancel();
        });
    }

    createTeacherForm(): void {
        this.teacherForm = this.fb.group({
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
                [Validators.required, Validators.email],
            ],
            username: [
                this.data.mainData.username,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
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
        this.teacherForm.reset();
        this.dialogRef.close({ success: true, data: teacher });
    }

    closeCancel(): void {
        this.teacherForm.reset();
        this.dialogRef.close({ success: false, data: null });
    }
}
