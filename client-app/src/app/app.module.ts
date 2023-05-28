import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FlexLayoutModule } from "@angular/flex-layout";

import { MatToolbarModule } from "@angular/material/toolbar";
import { MatListModule } from "@angular/material/list";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatSidenavModule } from "@angular/material/sidenav";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./components/header/header.component";
import { SidebarComponent } from "./components/sidebar/sidebar.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { ProfilePageComponent } from "./pages/profile-page/profile-page.component";
import { LogoutComponent } from "./components/logout/logout.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        SidebarComponent,
        LoginPageComponent,
        ProfilePageComponent,
        LogoutComponent,
        NotFoundPageComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,

        MatToolbarModule,
        MatListModule,
        MatButtonModule,
        MatIconModule,
        MatSidenavModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}
