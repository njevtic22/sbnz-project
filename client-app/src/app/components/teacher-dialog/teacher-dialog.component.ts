import {
    Component,
    Inject,
    OnInit,
    OnDestroy,
    ChangeDetectorRef,
} from "@angular/core";
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
import {
    clearBlacklist,
    validateForbiddenPassword,
} from "src/app/util/validator/no-forbidden-password-validator";
import { validateNumericalSequence } from "src/app/util/validator/illegal-numerical-sequence-validator";
import { validateAlphabeticalSequence } from "src/app/util/validator/illegal-alphabetical-sequence-validator";
import { validateQwertySequence } from "src/app/util/validator/illegal-qwerty-sequence-validator";
import { validateWhitespace } from "src/app/util/validator/no-whitespace-validator";
import { validateControlMatch } from "src/app/util/validator/control-match-validator";
import { animate, style, transition, trigger } from "@angular/animations";

@Component({
    selector: "app-teacher-dialog",
    templateUrl: "./teacher-dialog.component.html",
    styleUrls: ["./teacher-dialog.component.scss"],
    animations: [
        trigger("slideInOut", [
            transition(":enter", [style({ height: 0 }), animate(300)]),
            transition(":leave", [animate(300, style({ height: 0 }))]),
        ]),
    ],
})
export class TeacherDialogComponent implements OnInit, OnDestroy {
    teacherForm!: FormGroup;

    private takenEmails: string[] = [];
    private takenUsernames: string[] = [];

    maxDate = new Date();

    hidePassword: boolean = true;
    hideRepeatedPassword: boolean = true;
    hidePasswordDetails: boolean = true;

    private noNumerical: string = "Bez numeričke sekvence";
    private noAlphabetical: string = "Bez alfabetičke sekvence";
    private noQwerty: string = "Bez qwerty sekvence";
    private noWhitespace: string = "Bez razmaka";
    private noForbidden: string = "Bez zabranjene šifre";

    customMessage: string = this.noNumerical;
    private patternFail: string = "^(?!)";
    private patternMatch: string = ".*";
    invalidPasswordExpression: RegExp = new RegExp(this.patternFail);

    constructor(
        private fb: FormBuilder,
        private cd: ChangeDetectorRef,

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

    ngOnDestroy(): void {
        clearBlacklist();
    }

    createTeacherForm(): void {
        this.teacherForm = this.fb.group(
            {
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
                    [
                        Validators.required,
                        validateNumericalSequence(5),
                        validateAlphabeticalSequence(5),
                        validateQwertySequence(5),
                        validateWhitespace(),
                        validateForbiddenPassword(),
                    ],
                ],
                repeatedPassword: [
                    this.data.mainData.repeatedPassword,
                    Validators.required,
                ],
            },
            {
                validators: validateControlMatch(
                    "password",
                    "repeatedPassword"
                ),
            }
        );
    }

    preventPaste(event: Event): void {
        event.preventDefault();
    }

    updateRegex(event: Event): void {
        const passwordControl: AbstractControl =
            this.teacherForm.controls["password"];

        if (passwordControl.hasError("forbiddenPassword")) {
            this.customMessage = this.noForbidden;
        }

        if (passwordControl.hasError("qwertySequence")) {
            this.customMessage = this.noQwerty;
        }

        if (passwordControl.hasError("numericalSequence")) {
            this.customMessage = this.noNumerical;
        }

        if (passwordControl.hasError("alphabeticalSequence")) {
            this.customMessage = this.noAlphabetical;
        }

        if (passwordControl.hasError("whitespace")) {
            this.customMessage = this.noWhitespace;
        }

        const hasError: boolean =
            passwordControl.hasError("numericalSequence") ||
            passwordControl.hasError("alphabeticalSequence") ||
            passwordControl.hasError("qwertySequence") ||
            passwordControl.hasError("whitespace") ||
            passwordControl.hasError("forbiddenPassword");

        this.invalidPasswordExpression = hasError
            ? new RegExp(this.patternFail)
            : new RegExp(this.patternMatch);

        // Expression has changed after it was checked error
        // https://angular.io/errors/NG0100
        this.cd.detectChanges();

        // Hacky solutions, last resort, in this case it doesn't work
        // Promise.resolve().then(() => {
        // this.customMessage =
        //     this.customMessage === "Must fail"
        //         ? "Must not contain password"
        //         : "Must fail";
        // this.forbiddenPasswordExpression = this.loginForm.controls["password"]
        //     .errors?.["numericalSequence"]
        //     ? new RegExp(this.patternFail)
        //     : new RegExp(this.patternMatch);
        // });
    }

    getPasswordLength(password: string): number {
        if (!password) {
            return 0;
        }
        return password.length;
    }
    closeSubmit(): void {
        const teacher: User = this.teacherForm.value;
        this.teacherForm.reset();

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
