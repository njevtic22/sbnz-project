import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { ProfilePageComponent } from "./pages/profile-page/profile-page.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";
import { Role } from "./types/role";
import { AuthGuard } from "./guards/auth.guard";

const routes: Routes = [
    {
        path: "",
        pathMatch: "full",
        redirectTo: "profile",
    },
    {
        path: "login",
        component: LoginPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Login",
            roles: [Role.ROLE_ANONYMOUS],
        },
    },
    {
        path: "profile",
        component: ProfilePageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Profil",
            roles: [Role.ROLE_ADMIN, Role.ROLE_TEACHER, Role.ROLE_STUDENT],
        },
    },
    {
        path: "not-found",
        component: NotFoundPageComponent,
        data: {
            title: "404",
        },
    },
    {
        path: "**",
        redirectTo: "not-found",
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}