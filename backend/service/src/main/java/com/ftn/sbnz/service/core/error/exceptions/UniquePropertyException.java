package com.ftn.sbnz.service.core.error.exceptions;

public class UniquePropertyException extends RuntimeException {
    public UniquePropertyException(String message) {
        super(message);
    }

    public UniquePropertyException(String entityName, String propertyName, String propertyValue) {
        super(entityName + " with " + propertyName + "='" + propertyValue + "' already exist.");
    }

    public UniquePropertyException(String entityName, String propertyName, String propertyValue, Throwable cause) {
        super(entityName + " with " + propertyName + "='" + propertyValue + "' already exist.", cause);
    }
}
