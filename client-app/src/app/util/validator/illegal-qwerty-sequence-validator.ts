import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { hasSequence } from "./has-sequence";

const row1: string = "`1234567890-=";
const capitalRow1: string = "~!@#$%^&*()_+";

const row2: string = "qwertyuiop[]\\";
const capitalRow2: string = "QWERTYUIOP{}|";

const row3: string = "asdfghjkl;'";
const capitalRow3: string = 'ASDFGHJKL:"';

const row4: string = "zxcvbnm,./";
const capitalRow4: string = "ZXCVBNM<>?";

//
const reversedRow1: string = "=-0987654321`";
const reversedCapitalRow1: string = "+_)(*&^%$#@!~";

const reversedRow2: string = "\\][poiuytrewq";
const reversedCapitalRow2: string = "|}{POIUYTREWQ";

const reversedRow3: string = "';lkjhgfdsa";
const reversedCapitalRow3: string = '":LKJHGFDSA';

const reversedRow4: string = "/.,mnbvcxz";
const reversedCapitalRow4: string = "?><MNBVCXZ";

const illegalPool: Map<number, string[]> = new Map();

//
export function validateQwertySequence(sequenceLength: number): ValidatorFn {
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
                qwertySequence: {
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

    // sequenceLength > row1.length
    if (sequenceLength > 13) {
        return [
            row1,
            capitalRow1,
            row2,
            capitalRow2,
            row3,
            capitalRow3,
            row4,
            capitalRow4,
            //
            reversedRow1,
            reversedCapitalRow1,
            reversedRow2,
            reversedCapitalRow2,
            reversedRow3,
            reversedCapitalRow3,
            reversedRow4,
            reversedCapitalRow4,
        ];
    }

    if (illegalPool.has(sequenceLength)) {
        return illegalPool.get(sequenceLength) as string[];
    }

    const illegal: string[] = [];

    for (let i = 0; i <= row1.length - sequenceLength; i++) {
        const subRow1: string = row1.substring(i, i + sequenceLength);
        const subCapitalRow1: string = capitalRow1.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subRow1);
        illegal.push(subCapitalRow1);
    }

    for (let i = 0; i <= row2.length - sequenceLength; i++) {
        const subRow2: string = row2.substring(i, i + sequenceLength);
        const subCapitalRow2: string = capitalRow2.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subRow2);
        illegal.push(subCapitalRow2);
    }

    for (let i = 0; i <= row3.length - sequenceLength; i++) {
        const subRow3: string = row3.substring(i, i + sequenceLength);
        const subCapitalRow3: string = capitalRow3.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subRow3);
        illegal.push(subCapitalRow3);
    }

    for (let i = 0; i <= row4.length - sequenceLength; i++) {
        const subRow4: string = row4.substring(i, i + sequenceLength);
        const subCapitalRow4: string = capitalRow4.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subRow4);
        illegal.push(subCapitalRow4);
    }

    for (let i = 0; i <= reversedRow1.length - sequenceLength; i++) {
        const subReversedRow1: string = reversedRow1.substring(
            i,
            i + sequenceLength
        );
        const subReversedCapitalRow1: string = reversedCapitalRow1.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subReversedRow1);
        illegal.push(subReversedCapitalRow1);
    }

    for (let i = 0; i <= reversedRow2.length - sequenceLength; i++) {
        const subReversedRow2: string = reversedRow2.substring(
            i,
            i + sequenceLength
        );
        const subReversedCapitalRow2: string = reversedCapitalRow2.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subReversedRow2);
        illegal.push(subReversedCapitalRow2);
    }

    for (let i = 0; i <= reversedRow3.length - sequenceLength; i++) {
        const subReversedRow3: string = reversedRow3.substring(
            i,
            i + sequenceLength
        );
        const subReversedCapitalRow3: string = reversedCapitalRow3.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subReversedRow3);
        illegal.push(subReversedCapitalRow3);
    }

    for (let i = 0; i <= reversedRow4.length - sequenceLength; i++) {
        const subReversedRow4: string = reversedRow4.substring(
            i,
            i + sequenceLength
        );
        const subReversedCapitalRow4: string = reversedCapitalRow4.substring(
            i,
            i + sequenceLength
        );

        illegal.push(subReversedRow4);
        illegal.push(subReversedCapitalRow4);
    }

    illegalPool.set(sequenceLength, illegal);

    return illegal;
}
