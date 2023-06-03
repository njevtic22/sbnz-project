import {
    Component,
    OnInit,
    OnDestroy,
    Input,
    Output,
    EventEmitter,
} from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Role } from "src/app/types/role";
import { User } from "src/app/types/user";
import { validateLeadingTrailingWhitespace } from "src/app/util/validator/no-leading-trailing-whitespace";

@Component({
    selector: "app-profile-change",
    templateUrl: "./profile-change.component.html",
    styleUrls: ["./profile-change.component.scss"],
})
export class ProfileChangeComponent implements OnInit, OnDestroy {
    @Input() user!: User;
    @Output() profileChanged: EventEmitter<User> = new EventEmitter();

    @Input() errorOccurred!: boolean;
    @Input() errorMessage!: string;

    profileForm!: FormGroup;

    constructor(private fb: FormBuilder) {}

    ngOnInit(): void {
        this.createProfileForm();
    }

    ngOnDestroy(): void {
        //
    }

    createProfileForm(): void {
        this.profileForm = this.fb.group({
            id: [this.user.id, Validators.required],
            name: [
                this.user.name,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            surname: [
                this.user.surname,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            birthDate: [this.user.birthDate],
            email: [this.user.email, [Validators.required, Validators.email]],
            username: [
                this.user.username,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            role: [
                this.user.role,
                [Validators.required, validateLeadingTrailingWhitespace()],
            ],
            // nivoSklonosti: [this.user.nivoSklonosti, [Validators.required]],
        });
    }

    submit(event: Event): void {
        event.preventDefault();
        const updatedUser: User = this.profileForm.value;
        this.profileChanged.emit(updatedUser);
    }
}
