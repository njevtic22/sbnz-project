import { Component, Inject, OnInit, OnDestroy } from "@angular/core";
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
import { Moment } from "moment";

@Component({
    selector: "app-update-user-dialog",
    templateUrl: "./update-user-dialog.component.html",
    styleUrls: ["./update-user-dialog.component.scss"],
})
export class UpdateUserDialogComponent implements OnInit, OnDestroy {
    userForm!: FormGroup;

    private takenEmails: string[] = [];
    private takenUsernames: string[] = [];

    maxDate = new Date();
    startDate = new Date(1985, 6, 15);

    constructor(
        private fb: FormBuilder,
        @Inject(MAT_DIALOG_DATA)
        public data: ModalData<User>, // this is reference to original
        private dialogRef: MatDialogRef<UpdateUserDialogComponent>
    ) {}

    ngOnInit(): void {
        this.takenEmails = this.data.additionalData.takenEmails;
        this.takenUsernames = this.data.additionalData.takenUsernames;

        this.createUserForm();

        this.dialogRef.disableClose = true;
        this.dialogRef.backdropClick().subscribe(() => {
            this.closeCancel();
        });
    }

    ngOnDestroy(): void {
        //
    }

    createUserForm(): void {
        this.userForm = this.fb.group({
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
        });
    }

    closeSubmit(): void {
        const teacher: User = this.userForm.value;
        this.userForm.reset();

        const mDate: Moment = teacher.birthDate as Moment;

        let dateArray: number[] = [0, 0, 0];
        let dateStringArray: string[] = mDate.format("YYYY-MM-DD").split("-");

        dateArray[0] = Number(dateStringArray[0]);
        dateArray[1] = Number(dateStringArray[1]);
        dateArray[2] = Number(dateStringArray[2]);

        teacher.birthDate = dateArray;

        this.dialogRef.close({ success: true, data: teacher });
    }

    closeCancel(): void {
        this.userForm.reset();
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
