import { Component } from "@angular/core";
import { constants } from "src/app/constants";

@Component({
    selector: "app-not-found-page",
    templateUrl: "./not-found-page.component.html",
    styleUrls: ["./not-found-page.component.scss"],
})
export class NotFoundPageComponent {
    getImage(): string {
        return constants.notFoundImage;
    }
}
