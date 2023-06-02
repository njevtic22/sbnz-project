import { animate, style, transition, trigger } from "@angular/animations";
import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from "@angular/core";
import {
    AbstractControl,
    FormBuilder,
    FormGroup,
    Validators,
} from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Subscription } from "rxjs";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { UserService } from "src/app/services/user.service";
import { PasswordChange } from "src/app/types/password";
import { validateControlMatch } from "src/app/util/validator/control-match-validator";
import { validateAlphabeticalSequence } from "src/app/util/validator/illegal-alphabetical-sequence-validator";
import { validateNumericalSequence } from "src/app/util/validator/illegal-numerical-sequence-validator";
import { validateQwertySequence } from "src/app/util/validator/illegal-qwerty-sequence-validator";
import {
    clearBlacklist,
    validateForbiddenPassword,
} from "src/app/util/validator/no-forbidden-password-validator";
import { validateWhitespace } from "src/app/util/validator/no-whitespace-validator";

@Component({
    selector: "app-password-change",
    templateUrl: "./password-change.component.html",
    styleUrls: ["./password-change.component.scss"],
    animations: [
        trigger("slideInOut", [
            transition(":enter", [style({ height: 0 }), animate(300)]),
            transition(":leave", [animate(300, style({ height: 0 }))]),
        ]),
    ],
})
export class PasswordChangeComponent implements OnInit, OnDestroy {
    passwordForm!: FormGroup;
    hideOldPassword: boolean = true;
    hideNewPassword: boolean = true;
    hideNewPasswordDetails: boolean = true;

    private noNumerical: string = "Bez numeričke sekvence";
    private noAlphabetical: string = "Bez alfabetičke sekvence";
    private noQwerty: string = "Bez qwerty sekvence";
    private noWhitespace: string = "Bez razmaka";
    private noForbidden: string = "Bez zabranjene šifre";

    customMessage: string = this.noNumerical;
    private patternFail: string = "^(?!)";
    private patternMatch: string = ".*";
    invalidPasswordExpression: RegExp = new RegExp(this.patternFail);

    errorOccurred: boolean = false;
    errorMessage: string = "Unknown error";

    private userSubscription: Subscription = new Subscription();

    constructor(
        private fb: FormBuilder,
        private cd: ChangeDetectorRef,
        private snackbar: MatSnackBar,
        private userService: UserService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.createPasswordForm();
    }

    ngOnDestroy(): void {
        clearBlacklist();
        this.userSubscription.unsubscribe();
    }

    createPasswordForm(): void {
        this.passwordForm = this.fb.group(
            {
                oldPassword: ["", Validators.required],
                newPassword: [
                    "",
                    [
                        Validators.required,
                        validateNumericalSequence(5),
                        validateAlphabeticalSequence(5),
                        validateQwertySequence(5),
                        validateWhitespace(),
                        validateForbiddenPassword(),
                    ],
                ],
                repeatedPassword: ["", Validators.required],
            },
            {
                validators: validateControlMatch(
                    "newPassword",
                    "repeatedPassword"
                ),
            }
        );
    }

    preventPaste(event: Event): void {
        event.preventDefault();
    }

    changePassword(event: Event): void {
        event.preventDefault();

        const passwordData: PasswordChange = this.passwordForm.value;
        this.userSubscription.unsubscribe();
        this.userSubscription = this.userService
            .changePassword(passwordData)
            .subscribe(
                () => {
                    this.passwordForm.reset();
                    this.snackbar.open("Vaša šifra je promenjena.", "Zatvori", {
                        duration: 5 * 1000,
                    });

                    this.errorOccurred = false;
                    this.errorMessage = "Unknown error";
                },
                (errorResponse: HttpErrorResponse) => {
                    if (errorResponse.status === 400) {
                        this.errorOccurred = true;
                        this.errorMessage = errorResponse.error.message;
                        if (this.errorMessage === "Incorrect password.") {
                            this.errorMessage = "Neispravna trenutna sifra.";
                        }
                    } else {
                        this.errorHandler.handle(errorResponse);
                    }
                }
            );
    }

    updateRegex(event: Event): void {
        const passwordControl: AbstractControl =
            this.passwordForm.controls["newPassword"];

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
}
