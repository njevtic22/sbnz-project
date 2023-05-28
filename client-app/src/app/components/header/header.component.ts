import {
    Component,
    OnInit,
    OnDestroy,
    Output,
    EventEmitter,
} from "@angular/core";
import { ActivatedRoute, NavigationEnd, Router } from "@angular/router";
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
        // https://stackoverflow.com/a/49813876
        this.routerSubscription = this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                let route: ActivatedRoute | null = this.route.firstChild;
                let child: ActivatedRoute | null = route;

                while (child?.firstChild) {
                    route = child;
                    child = child.firstChild;
                }
                this.title = route?.snapshot.data["title"];
            }
        });
    }

    ngOnDestroy(): void {
        this.routerSubscription.unsubscribe();
    }
}
