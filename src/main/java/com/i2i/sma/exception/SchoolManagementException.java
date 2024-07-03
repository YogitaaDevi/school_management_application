package com.i2i.sma.exception;

public class SchoolManagementException extends Exception {
    public SchoolManagementException(String message) {
        super(message);
    }

    public SchoolManagementException(String message, Throwable e) {
        super(message, e);
    }
}