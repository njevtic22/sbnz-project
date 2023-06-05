export interface ModalData<T1, T2 = any> {
    mainData: T1;
    additionalData: T2;
}

export interface ModalResult<T> {
    success: boolean;
    data: T;
}
