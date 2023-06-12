import { KeyValuePair } from "./pair";
import { NivoNasilja, TipNasilja, StructuredViolences } from "./violence-types";

export function getNivoNasiljaKeyByValue(value: NivoNasilja): string {
    const index = Object.values(NivoNasilja).indexOf(value);
    return Object.keys(NivoNasilja)[index];
}

export function getTipNasiljaKeyByValue(value: TipNasilja): string {
    const index = Object.values(TipNasilja).indexOf(value);
    return Object.keys(TipNasilja)[index];
}

export function getNivoNasiljaKeyValuePairs(): KeyValuePair<string, string>[] {
    const keyValues: KeyValuePair<string, string>[] = [];

    const values: string[] = Object.values(NivoNasilja);
    values.forEach((value: string) => {
        keyValues.push({
            key: getNivoNasiljaKeyByValue(value as NivoNasilja),
            value: value,
        });
    });

    return keyValues;
}

export function getTipNasiljaKeyValuePairs(): KeyValuePair<string, string>[] {
    const keyValues: KeyValuePair<string, string>[] = [];

    const values: string[] = Object.values(TipNasilja);
    values.forEach((value: string) => {
        keyValues.push({
            key: getTipNasiljaKeyByValue(value as TipNasilja),
            value: value,
        });
    });

    return keyValues;
}

export function getOblikNasiljaKeyValuePairs(
    nivoKey: string,
    tipKey: string
): KeyValuePair<string, string>[] {
    const keyValues: KeyValuePair<string, string>[] = [];

    const obliciNasilja = StructuredViolences[nivoKey][tipKey];

    const obliciKeys: string[] = Object.keys(
        StructuredViolences[nivoKey][tipKey]
    );
    obliciKeys.forEach((key: string) => {
        keyValues.push({
            key: key,
            value: obliciNasilja[key],
        });
    });

    return keyValues;
}
