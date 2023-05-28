import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: "app-sidebar",
    templateUrl: "./sidebar.component.html",
    styleUrls: ["./sidebar.component.scss"],
})
export class SidebarComponent {
    opened: boolean = true;

    constructor(private router: Router) {}

    toggle(): void {
        this.opened = !this.opened;
    }
}
