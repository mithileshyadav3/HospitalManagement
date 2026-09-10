package com.appointment.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends  RuntimeException{
    private  final HttpStatus httpStatus;

    public InvalidCredentialsException(String message) {
        super(message);
        this.httpStatus =HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
