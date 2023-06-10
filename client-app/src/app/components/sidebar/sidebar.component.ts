import { Component } from "@angular/core";
import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
    selector: "app-sidebar",
    templateUrl: "./sidebar.component.html",
    styleUrls: ["./sidebar.component.scss"],
})
export class SidebarComponent {
    opened: boolean = true;

    constructor(private authService: AuthenticationService) {}

    toggle(): void {
        this.opened = !this.opened;
    }

    isAuthenticated(): boolean {
        return this.authService.isAuthenticated();
    }

    isAdmin(): boolean {
        return this.authService.isAdmin();
    }

    isTeacher(): boolean {
        return this.authService.isTeacher();
    }

    isStudent(): boolean {
        return this.authService.isStudent();
    }
}
