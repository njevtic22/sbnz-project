package com.ftn.sbnz.service.core.error.exceptions;

public class StaresinaTakenException extends RuntimeException{
    public StaresinaTakenException(Long teacherId) {
        super("Teacher with id='" + teacherId + "' is already staresina.");
    }
}
