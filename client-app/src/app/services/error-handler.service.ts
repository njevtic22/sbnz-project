import { HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
    providedIn: "root",
})
export class ErrorHandlerService {
    constructor(private router: Router, private snackbar: MatSnackBar) {}

    handle = (errorResponse: HttpErrorResponse): void => {
        // const status: number = errorResponse.status;
        // const error = errorResponse.error;
        // const timestamp: number[] = error.timestamp;
        // const message: string = error.message;

        // Springs error response
        // {
        //     "type": "about:blank",
        //     "title": "Bad Request",
        //     "status": 400,
        //     "detail": "Failed to convert 'id' with value: 'nemanja'",
        //     "instance": "/api/csrs/nemanja/verify"
        // }

        // ApiError
        // {
        //     "timestamp": [
        //         2023,
        //         4,
        //         27,
        //         14,
        //         32,
        //         40,
        //         774197800
        //     ],
        //     "message": "Certificate Signing Request with id: '101' not found."
        // }

        const status: number = errorResponse.status;
        if (status === 404) {
            this.router.navigate(["not-found"]);
        }

        let message: string = errorResponse.error.message;

        if (
            errorResponse.error.title &&
            errorResponse.error.status &&
            errorResponse.error.detail
        ) {
            message =
                errorResponse.error.title + ": " + errorResponse.error.detail;
        }

        if (message === "Invalid field(s).") {
            message = this.parseInvalidFieldErrors(errorResponse);
        }

        this.snackbar.open(
            message,
            "Close"
            // {
            //     // duration: duration * 1000,
            //     // horizontalPosition: "center",
            //     // verticalPosition: "bottom",
            // }
        );
    };

    private parseInvalidFieldErrors(errorResponse: HttpErrorResponse): string {
        let fieldErrors: string[] = [];
        for (const detailError of errorResponse.error.details) {
            for (const message of detailError.messages) {
                fieldErrors.push(message);
            }
        }
        return fieldErrors.join(" ");
    }
}
