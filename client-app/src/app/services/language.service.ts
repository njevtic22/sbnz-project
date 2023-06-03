import { Injectable } from "@angular/core";

@Injectable({
    providedIn: "root",
})
export class LanguageService {
    private badCredentials: string = "Pogrešno korisničko ime ili lozinka.";
    private incorrectPassword: string = "Neispravna trenutna sifra.";
    private takenEmailStart = "Imejl '";
    private takenEmailEnd = "' je već zauzet.";
    private takenUsernameStart = "Korisničko ime '";
    private takenUsernameEnd = "' je već zauzeto.";

    constructor() {}

    translateBadCredentials(original: string): string {
        return this.badCredentials;
    }

    translateIncorrectPassword(original: string): string {
        return this.incorrectPassword;
    }

    translateTakenEmail(original: string): string {
        return (
            this.takenEmailStart +
            original.substring(
                original.indexOf("'") + 1,
                original.lastIndexOf("'")
            ) +
            this.takenEmailEnd
        );
    }

    translateTakenUsername(original: string): string {
        return (
            this.takenUsernameStart +
            original.substring(
                original.indexOf("'") + 1,
                original.lastIndexOf("'")
            ) +
            this.takenUsernameEnd
        );
    }
}
