import { Component, Input } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
    selector: "app-logout",
    templateUrl: "./logout.component.html",
    styleUrls: ["./logout.component.scss"],
})
export class LogoutComponent {
    @Input() buttonClass: string = "";

    constructor(
        private router: Router,
        private authService: AuthenticationService
    ) {}

    logout(): void {
        this.authService.logout();
        this.router.navigate(["login"]);
    }
}
