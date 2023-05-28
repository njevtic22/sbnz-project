import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

const nonWhitespaceRegex: RegExp = /^\S+$/;

// const whitespaceRegex: RegExp = /^\s+$/;

export function validateWhitespace(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const value: string = control.value;

        if (!value) {
            return null;
        }

        if (nonWhitespaceRegex.test(value)) {
            return null;
        }

        return {
            whitespace: {
                valid: false,
            },
        };
    };
}
