import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { hasSequence } from "./has-sequence";

const letters: string = "abcdefghijklmnopqrstuvwxyz";
const capitalLetters: string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const reversedLetters: string = "zyxwvutsrqponmlkjihgfedcba";
const reversedCapitalLetters: string = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

const illegalPool: Map<number, string[]> = new Map();

export function validateAlphabeticalSequence(
    sequenceLength: number
): ValidatorFn {
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
                alphabeticalSequence: {
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

    if (sequenceLength > letters.length) {
        return [
            letters,
            reversedLetters,
            capitalLetters,
            reversedCapitalLetters,
        ];
    }

    if (illegalPool.has(sequenceLength)) {
        return illegalPool.get(sequenceLength) as string[];
    }

    const illegal: string[] = [];

    for (let i = 0; i <= letters.length - sequenceLength; i++) {
        const subLetters: string = letters.substring(i, i + sequenceLength);
        const subCapitalLetters: string = capitalLetters.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subLetters);
        illegal.push(subCapitalLetters);
    }

    for (let i = 0; i <= reversedLetters.length - sequenceLength; i++) {
        const subReversedLetters: string = reversedLetters.substring(
            i,
            i + sequenceLength
        );
        const subReversedCapitalLetters: string =
            reversedCapitalLetters.substring(i, i + sequenceLength);

        illegal.push(subReversedLetters);
        illegal.push(subReversedCapitalLetters);
    }

    illegalPool.set(sequenceLength, illegal);

    return illegal;
}
