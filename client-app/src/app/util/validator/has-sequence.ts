export function hasSequence(value: string, sequences: string[]): boolean {
    for (const sequence of sequences) {
        if (value.includes(sequence)) {
            return true;
        }
    }
    return false;
}
