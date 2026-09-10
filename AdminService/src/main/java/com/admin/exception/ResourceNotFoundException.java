package com.admin.exception;

import org.springframework.http.HttpStatus;



public class ResourceNotFoundException extends RuntimeException{
    private  String message;
    private HttpStatus httpStatus;

    public ResourceNotFoundException(String message) {
        this.message = message;
        this.httpStatus= HttpStatus.NOT_FOUND;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public String getMessage() {
        return message;
    }
}
