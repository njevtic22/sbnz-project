package com.ftn.sbnz.service.core.error;

import java.util.ArrayList;
import java.util.List;

public class ApiFieldError extends ApiError {
    private final List<FieldErrorMessage> details;

    public ApiFieldError() {
        super();
        this.details = new ArrayList<>();
    }

    public ApiFieldError(String message, List<FieldErrorMessage> details) {
        super(message);
        this.details = details;
    }

    public List<FieldErrorMessage> getDetails() {
        return details;
    }
}
