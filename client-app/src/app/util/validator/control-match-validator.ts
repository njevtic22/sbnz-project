import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function validateControlMatch(
    controlName: string,
    matchingControlName: string
): ValidatorFn {
    return (form: AbstractControl): ValidationErrors | null => {
        const control: AbstractControl = form.get(
            controlName
        ) as AbstractControl;
        const matchingControl: AbstractControl = form.get(
            matchingControlName
        ) as AbstractControl;

        if (control.errors && !control.errors["mustMatch"]) {
            // return null if another validator has already found an error on controlName
            return null;
        }

        if (matchingControl.errors && !matchingControl.errors["mustMatch"]) {
            // return null if another validator has already found an error on matchingControl
            return null;
        }

        // set error on control and matchingControl if validation fails
        if (control.value !== matchingControl.value) {
            control.setErrors({ mustMatch: true });
            matchingControl.setErrors({ mustMatch: true });
            return { mustMatch: true };
        } else {
            if (control.hasError("mustMatch")) {
                control.setErrors(null);
            }
            if (matchingControl.hasError("mustMatch")) {
                matchingControl.setErrors(null);
            }
        }

        return null;
    };
}
