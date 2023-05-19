package com.ftn.sbnz.service.core.error;

import java.util.List;

public class FieldErrorMessage {
    private final String field;
    private final Object rejectedValue;     // may cause infinite json serialization
    private final List<String> messages;

    public FieldErrorMessage(String field, Object rejectedValue, List<String> messages) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.messages = messages;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public List<String> getMessages() {
        return messages;
    }
}

