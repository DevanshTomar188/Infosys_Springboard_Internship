package com.infy.license.model;

public class ErrorValidation {
    private String errorMessage;

    public ErrorValidation(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
