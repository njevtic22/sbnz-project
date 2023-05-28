import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { ProfilePageComponent } from "./pages/profile-page/profile-page.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";

const routes: Routes = [
    {
        path: "",
        pathMatch: "full",
        redirectTo: "profile",
    },
    {
        path: "login",
        component: LoginPageComponent,
        data: {
            title: "Login",
        },
    },
    {
        path: "profile",
        component: ProfilePageComponent,
        data: {
            title: "Profil",
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
