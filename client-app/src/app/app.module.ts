import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FlexLayoutModule } from "@angular/flex-layout";

import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";

import { MatToolbarModule } from "@angular/material/toolbar";
import { MatListModule } from "@angular/material/list";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTabsModule } from "@angular/material/tabs";
import { MatTableModule } from "@angular/material/table";
import { MatDialogModule } from "@angular/material/dialog";
import { MatSelectModule } from "@angular/material/select";

import { MatPasswordStrengthModule } from "@angular-material-extensions/password-strength";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./components/header/header.component";
import { SidebarComponent } from "./components/sidebar/sidebar.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { ProfilePageComponent } from "./pages/profile-page/profile-page.component";
import { LogoutComponent } from "./components/logout/logout.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";
import { ProfileChangeComponent } from "./components/profile-change/profile-change.component";
import { JwtInterceptor } from "./interceptors/jwt.interceptor";
import { PasswordChangeComponent } from "./components/password-change/password-change.component";
import { ClassesPageComponent } from "./pages/classes-page/classes-page.component";
import { ClassDialogComponent } from "./components/class-dialog/class-dialog.component";
import { TeachersPageComponent } from './pages/teachers-page/teachers-page.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        SidebarComponent,
        LoginPageComponent,
        ProfilePageComponent,
        LogoutComponent,
        NotFoundPageComponent,
        ProfileChangeComponent,
        PasswordChangeComponent,
        ClassesPageComponent,
        ClassDialogComponent,
        TeachersPageComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,

        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,

        MatToolbarModule,
        MatListModule,
        MatButtonModule,
        MatIconModule,
        MatSidenavModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatSnackBarModule,
        MatTabsModule,
        MatTableModule,
        MatDialogModule,
        MatSelectModule,

        MatPasswordStrengthModule.forRoot(),
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
