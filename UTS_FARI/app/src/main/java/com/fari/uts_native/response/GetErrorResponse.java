package com.fari.uts_native.response;

public class GetErrorResponse<T> {
    private String message;
    private T validationErrors;

    public String getMessage() {
        return message;
    }

    public T getValidationErrors() {
        return validationErrors;
    }
}