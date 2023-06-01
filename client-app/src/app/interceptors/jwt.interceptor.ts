import { Injectable } from "@angular/core";
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
} from "@angular/common/http";
import { Observable } from "rxjs";
import { AuthenticationService } from "../services/authentication.service";
import { environment } from "src/environment/environment";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authService: AuthenticationService) {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        if (!this.authService.isAuthenticated()) {
            return next.handle(request);
        }

        const token: string = sessionStorage.getItem("token") as string;
        const isApiUrl: boolean = request.url.startsWith(environment.apiUrl);

        if (!isApiUrl) {
            return next.handle(request);
        }

        const cloned: HttpRequest<unknown> = request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`,
            },
        });
        return next.handle(cloned);
    }
}
