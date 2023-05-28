import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Subscription } from "rxjs";

import { AuthenticationService } from "src/app/services/authentication.service";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { validateWhitespace } from "src/app/util/validator/no-whitespace-validator";
import { validateLeadingTrailingWhitespace } from "src/app/util/validator/no-leading-trailing-whitespace";
import { LoginRequest, LoginResponse } from "src/app/types/login";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: "app-login-page",
    templateUrl: "./login-page.component.html",
    styleUrls: ["./login-page.component.scss"],
})
export class LoginPageComponent implements OnInit, OnDestroy {
    loginForm!: FormGroup;
    hidePassword: boolean = true;

    errorOccurred: boolean = false;
    errorMessage: string = "Bad credentials";

    private loginSubscription: Subscription = new Subscription();

    constructor(
        private fb: FormBuilder,
        private router: Router,
        private authService: AuthenticationService,
        private errorHandler: ErrorHandlerService
    ) {}

    ngOnInit(): void {
        this.loginForm = this.fb.group({
            username: [
                "",
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            password: ["", [Validators.required, validateWhitespace()]],
        });
    }

    ngOnDestroy(): void {
        this.loginSubscription.unsubscribe();
    }

    login(): void {
        // const loginData: LoginRequest = this.loginForm.value as LoginRequest;
        const loginData: LoginRequest = this.loginForm.value;

        this.loginSubscription = this.authService.login(loginData).subscribe(
            (loginResponse: LoginResponse) => {
                sessionStorage.setItem("token", loginResponse.token);
                sessionStorage.setItem("role", loginResponse.role);

                this.router.navigate(["profile"]);
            },
            (errorResponse: HttpErrorResponse) => {
                if (
                    errorResponse.error.message === "Bad credentials" ||
                    errorResponse.status === 400
                ) {
                    this.errorOccurred = true;
                    this.errorMessage = errorResponse.error.message;
                } else {
                    this.errorHandler.handle(errorResponse);
                }
            }
        );
    }

    preventPaste(event: Event): void {
        event.preventDefault();
    }
}
