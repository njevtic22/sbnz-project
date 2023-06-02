import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { passwordBlacklist } from "src/app/types/password-blacklist";

let blacklist: string[] = [];

export function validateForbiddenPassword(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        // console.log(blacklist);

        const value: string = control.value;

        if (!value) {
            return null;
        }

        if (!shouldReadBlacklist(control)) {
            // console.log("Blacklist will not be read");
            return null;
        }

        if (blacklist.length === 0) {
            blacklist = passwordBlacklist;

            // console.log("Blacklist will be read");

            // async function readWholeFile(filePath: string): Promise<void> {
            //     const response = await fetch(filePath);
            //     const text = await response.text();
            //     // return text.split(/\r\n/);
            //     blacklist = text.split(/\s+/);
            // }

            // // console.log(blacklist);

            // readWholeFile(constants.passwordBlacklist).then();

            // console.log(blacklist);
        }
        // else {
        //     console.log("Blacklist already loaded");
        // }

        // console.log(blacklist);
        // console.log("Reading blacklist array");

        if (blacklist.includes(value)) {
            // console.log("FORBIDDEN PASSWORD");
            return {
                forbiddenPassword: {
                    valid: false,
                },
            };
        }

        return null;
    };
}

export function clearBlacklist(): void {
    blacklist = [];
    // console.log(blacklist);
}

function shouldReadBlacklist(passwordControl: AbstractControl): boolean {
    /**
     * I am aware that doing other validations here has no sense.
     *
     * But reading a file with 1_000_000 lines and storing those
     * lines in array can be time and/or memory expensive, which
     * could affect performance.
     *
     * Therefore, file is read only when other validations are passed.
     * Example: if password is shorter than 8 (7 because reading file has delay of 1 input)
     *          it is already invalid. Therefore it is not neccessary to read file,
     *          because password is invalid either way.
     */
    const password = passwordControl.value;

    if (password.length < 7 || password.length > 50) {
        return false;
    }

    if (!uppercaseRegex.test(password)) {
        return false;
    }

    if (!lowercaseRegex.test(password)) {
        return false;
    }

    // if (!digitRegex.test(password)) {
    //     return false;
    // }

    // if (!hasIllegalSequence(password, specialSequence)) {
    //     return false;
    // }

    // Has some wierd delay on 1 charachter input
    // Example: _P@ssw0rd then delete _ (whitespace)
    // if (passwordControl.hasError("alphabeticalSequence")) {
    //     return false;
    // }

    // if (passwordControl.hasError("numericalSequence")) {
    //     return false;
    // }

    // if (passwordControl.hasError("qwertySequence")) {
    //     return false;
    // }

    // if (passwordControl.hasError("whitespace")) {
    //     return false;
    // }

    return true;
}

const uppercaseRegex: RegExp = /.*[A-Z].*/;
const lowercaseRegex: RegExp = /.*[a-z].*/;
const digitRegex: RegExp = /.*\d.*/;

// const specialSequence: string[] = [
//     "!",
//     '"',
//     "#",
//     "$",
//     "%",
//     "&",
//     "'",
//     "(",
//     ")",
//     "*",
//     "+",
//     ",",
//     "-",
//     ".",
//     "/",
//     ":",
//     ";",
//     "<",
//     "=",
//     ">",
//     "?",
//     "@",
//     "[",
//     "\\",
//     "]",
//     "^",
//     "_",
//     "`",
//     "{",
//     "|",
//     "}",
//     "~",
//     "¡",
//     "¢",
//     "£",
//     "¤",
//     "¥",
//     "¦",
//     "§",
//     "¨",
//     "©",
//     "ª",
//     "«",
//     "¬",
//     "­",
//     "®",
//     "¯",
//     "°",
//     "±",
//     "²",
//     "³",
//     "´",
//     "µ",
//     "¶",
//     "·",
//     "¸",
//     "¹",
//     "º",
//     "»",
//     "¼",
//     "½",
//     "¾",
//     "¿",
//     "×",
//     "÷",
//     "–",
//     "—",
//     "―",
//     "‗",
//     "‘",
//     "’",
//     "‚",
//     "‛",
//     "“",
//     "”",
//     "„",
//     "†",
//     "‡",
//     "•",
//     "…",
//     "‰",
//     "′",
//     "″",
//     "‹",
//     "›",
//     "‼",
//     "‾",
//     "⁄",
//     "⁊",
//     "₠",
//     "₡",
//     "₢",
//     "₣",
//     "₤",
//     "₥",
//     "₦",
//     "₧",
//     "₨",
//     "₩",
//     "₪",
//     "₫",
//     "€",
//     "₭",
//     "₮",
//     "₯",
//     "₰",
//     "₱",
//     "₲",
//     "₳",
//     "₴",
//     "₵",
//     "₶",
//     "₷",
//     "₸",
//     "₹",
//     "₺",
//     "₻",
//     "₼",
//     "₽",
//     "₾",
// ];
