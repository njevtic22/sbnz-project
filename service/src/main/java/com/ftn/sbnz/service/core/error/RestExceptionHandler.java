package com.ftn.sbnz.service.core.error;

import com.ftn.sbnz.service.core.error.exceptions.EntityNotFoundException;
import com.ftn.sbnz.service.core.error.exceptions.InvalidPasswordException;
import com.ftn.sbnz.service.core.error.exceptions.MultipleDeletedRowsException;
import com.ftn.sbnz.service.core.error.exceptions.UniquePropertyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ArrayList<FieldErrorMessage> details = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();

            // There might be risk of infinite serialization because of loop
            Object rejectedValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage() == null ? "Default message is: null" : fieldError.getDefaultMessage();

            List<String> messages = Arrays.asList(message.split("\\|"));
            details.add(new FieldErrorMessage(field, rejectedValue, messages));
        }

        ApiFieldError apiError = new ApiFieldError("Invalid field(s).", details);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler({
            UniquePropertyException.class,
            InvalidPasswordException.class
    })
    public ResponseEntity<ApiError> handleBadRequest(RuntimeException ex) {
        ApiError apiError = new ApiError(ex.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler({
            EntityNotFoundException.class
    })
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex) {
        ApiError apiError = new ApiError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler({
            MultipleDeletedRowsException.class,
            NoSuchElementException.class,

            Exception.class
    })
    public ResponseEntity<ApiError> handleInternalServer(Exception ex) {
        ApiError apiError = new ApiError(ex.getMessage());
        return ResponseEntity.internalServerError().body(apiError);
    }
}
