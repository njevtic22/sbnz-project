import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { hasSequence } from "./has-sequence";

const digits: string = "0123456789";
const reversedDigits: string = "9876543210";

const illegalPool: Map<number, string[]> = new Map();

export function validateNumericalSequence(sequenceLength: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const value: string = control.value;

        if (!value) {
            return null;
        }

        // when null is returned, that means it has no ValidationErrors
        // i.e. value is valid
        if (value.length < sequenceLength) {
            return null;
        }

        const illegal: string[] = generateIllegalSequences(sequenceLength);
        if (hasSequence(value, illegal)) {
            return {
                numericalSequence: {
                    valid: false,
                },
            };
        }

        return null;
    };
}

function generateIllegalSequences(sequenceLength: number): string[] {
    if (sequenceLength <= 0) {
        throw new Error("Sequence length must be greater than 0.");
    }

    if (sequenceLength > digits.length) {
        return [digits, reversedDigits];
    }

    if (illegalPool.has(sequenceLength)) {
        return illegalPool.get(sequenceLength) as string[];
    }

    const illegal: string[] = [];

    for (let i = 0; i <= digits.length - sequenceLength; i++) {
        const subDigits: string = digits.substring(i, i + sequenceLength);
        illegal.push(subDigits);
    }

    for (let i = 0; i <= reversedDigits.length - sequenceLength; i++) {
        const subReversedDigits: string = reversedDigits.substring(
            i,
            i + sequenceLength
        );
        illegal.push(subReversedDigits);
    }

    illegalPool.set(sequenceLength, illegal);

    return illegal;
}
