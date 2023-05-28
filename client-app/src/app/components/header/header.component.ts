import {
    Component,
    OnInit,
    OnDestroy,
    Output,
    EventEmitter,
} from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Subscription } from "rxjs";

@Component({
    selector: "app-header",
    templateUrl: "./header.component.html",
    styleUrls: ["./header.component.scss"],
})
export class HeaderComponent implements OnInit, OnDestroy {
    @Output() menuClick = new EventEmitter();
    title: string = "";
    private routerSubscription: Subscription = new Subscription();

    constructor(private router: Router, private route: ActivatedRoute) {}

    ngOnInit(): void {
        // TODO: Implement When Routes are added
    }

    ngOnDestroy(): void {
        this.routerSubscription.unsubscribe();
    }
}
