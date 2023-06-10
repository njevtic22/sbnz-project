import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { ProfilePageComponent } from "./pages/profile-page/profile-page.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";
import { Role } from "./types/role";
import { AuthGuard } from "./guards/auth.guard";
import { ClassesPageComponent } from "./pages/classes-page/classes-page.component";
import { TeachersPageComponent } from "./pages/teachers-page/teachers-page.component";
import { StudentsPerClassPageComponent } from "./pages/students-per-class-page/students-per-class-page.component";
import { StudentsForTeacherPageComponent } from "./pages/students-for-teacher-page/students-for-teacher-page.component";
import { HistoryPageComponent } from "./pages/history-page/history-page.component";

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
            title: "Prijava",
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
        path: "classes",
        component: ClassesPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Odeljenja",
            roles: [Role.ROLE_ADMIN],
        },
    },
    {
        path: "classes/:id/students",
        component: StudentsPerClassPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Učenici",
            roles: [Role.ROLE_ADMIN],
        },
    },
    {
        path: "teachers",
        component: TeachersPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Profesori",
            roles: [Role.ROLE_ADMIN],
        },
    },
    {
        path: "students",
        component: StudentsForTeacherPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Učenici",
            roles: [Role.ROLE_TEACHER],
        },
    },
    {
        path: "students/:id/history",
        component: HistoryPageComponent,
        canActivate: [AuthGuard],
        data: {
            title: "Karton",
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
