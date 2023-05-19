package com.ftn.sbnz.service.core.error.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

