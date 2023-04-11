package com.souraj.demo3.exceptions;

public class CustomAccessDeniedExceptionResponse {

    private String message;

    public CustomAccessDeniedExceptionResponse() {
        this.message = "Unauthorized access.You do not have permission to access.";
    }
}
