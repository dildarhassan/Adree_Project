package com.example.StudentDataManagement.exception;

public class ValidationException extends RuntimeException {
    private final int rowNumber;
    private final String errorDescription;

    public ValidationException(int rowNumber, String errorDescription) {
        super("Row " + rowNumber + ": " + errorDescription);
        this.rowNumber = rowNumber;
        this.errorDescription = errorDescription;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
