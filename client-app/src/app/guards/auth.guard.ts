import { Injectable } from "@angular/core";
import {
    Router,
    CanActivate,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
} from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";
import { Role } from "../types/role";

@Injectable({ providedIn: "root" })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authService: AuthenticationService
    ) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean {
        let currentRole: string = this.authService.getRole();
        const routerRoles: string[] = route.data["roles"].map(
            (role: Role) => Role[role]
        );

        if (routerRoles.includes(currentRole)) {
            return true;
        }

        // this.router.navigate(['/'], { queryParams: { returnUrl: state.url } });
        this.router.navigate(["not-found"]);
        return false;
    }
}
