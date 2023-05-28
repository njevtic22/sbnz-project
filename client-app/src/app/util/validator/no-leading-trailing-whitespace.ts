import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function validateLeadingTrailingWhitespace(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const value: string = control.value;

        // added if (!value || ...) in order not to check
        // empty string and other falsy values
        // if value is falsy, then trim() will not be called
        // due to short circuit evaluation
        if (!value || value.trim() === value) {
            return null;
        }

        return {
            leadingTrailingSpace: {
                valid: false,
            },
        };
    };
}
