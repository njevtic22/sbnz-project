import { Component, OnInit, OnDestroy } from "@angular/core";
import { Subscription } from "rxjs";
import { trigger, transition, animate, style } from "@angular/animations";
import { ErrorHandlerService } from "src/app/services/error-handler.service";
import { MatSnackBar } from "@angular/material/snack-bar";
import { UpdateUserResponse, User } from "src/app/types/user";
import { UserService } from "src/app/services/user.service";
import { constants } from "src/app/constants";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthenticationService } from "src/app/services/authentication.service";
import { HttpErrorResponse } from "@angular/common/http";
import { LanguageService } from "src/app/services/language.service";

@Component({
    selector: "app-profile-page",
    templateUrl: "./profile-page.component.html",
    styleUrls: ["./profile-page.component.scss"],
    animations: [
        trigger("slideInOut", [
            transition(":enter", [style({ height: 0 }), animate(300)]),
            transition(":leave", [animate(300, style({ height: 0 }))]),
        ]),
    ],
})
export class ProfilePageComponent implements OnInit, OnDestroy {
    private userSubscription: Subscription = new Subscription();

    user: User = {
        id: 0,
        name: "",
        surname: "",
        birthDate: [0, 0, 0],
        jmbg: "",
        email: "",
        username: "",
        role: "",
        nivoSklonosti: "",
    };

    roleToShow: string = "";
    nivoToShow: string = "";
    dateToShow: string = "";

    hideTabs: boolean = true;

    errorOccurred: boolean = false;
    errorMessage: string = "Unknown error";

    private roleMap: any = {
        ROLE_ADMIN: "Administrator",
        ROLE_TEACHER: "Profesor",
        ROLE_STUDENT: "Učenik",
    };

    constructor(
        private userService: UserService,
        private errorHandler: ErrorHandlerService,
        private authService: AuthenticationService,
        private languageService: LanguageService,
        private snackbar: MatSnackBar
    ) {}

    ngOnInit(): void {
        this.getProfile();
    }

    ngOnDestroy(): void {
        this.userSubscription.unsubscribe();
    }

    changeProfile(user: User): void {
        this.userSubscription.unsubscribe();
        this.userSubscription = this.userService.update(user).subscribe(
            (response: UpdateUserResponse) => {
                this.user = response.updated;
                this.snackbar.open("Vaš profil je izmenjen.", "Zatvori", {
                    duration: 5 * 1000,
                });

                this.errorOccurred = false;
                this.errorMessage = "Unknown error";

                if (response.token) {
                    sessionStorage.setItem("token", response.token);
                }
            },
            (errorResponse: HttpErrorResponse) => {
                if (errorResponse.status === 400) {
                    this.errorOccurred = true;
                    this.errorMessage = errorResponse.error.message;

                    if (this.errorMessage.startsWith("Email ")) {
                        this.errorMessage =
                            this.languageService.translateTakenEmail(
                                this.errorMessage
                            );
                    } else if (this.errorMessage.startsWith("Username ")) {
                        this.errorMessage =
                            this.languageService.translateTakenUsername(
                                this.errorMessage
                            );
                    }
                } else {
                    this.errorHandler.handle(errorResponse);
                }
            }
        );
    }

    getProfile(): void {
        this.userSubscription = this.userService
            .getProfile()
            .subscribe((user: User) => {
                this.user = user;
                this.roleToShow = this.roleMap[this.user.role];
                this.dateToShow = `${this.user.birthDate[2]}.${this.user.birthDate[1]}.${this.user.birthDate[0]}.`;

                if (this.user.nivoSklonosti) {
                    this.nivoToShow = this.user.nivoSklonosti?.charAt(0);
                    this.nivoToShow += this.user.nivoSklonosti
                        ?.substring(1)
                        .toLowerCase();
                }
            }, this.errorHandler.handle);
    }

    getImage(): string {
        return constants.userLogoImage;
    }

    isStudent(): boolean {
        return this.authService.isStudent();
    }
}
