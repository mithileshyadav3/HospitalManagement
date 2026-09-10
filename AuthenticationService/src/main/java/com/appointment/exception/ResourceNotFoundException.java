package com.appointment.exception;


import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends  RuntimeException {
    ;
    private HttpStatus httpStatus;
    public ResourceNotFoundException(String message) {
       super(message);
     this.httpStatus=HttpStatus.NOT_FOUND;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }





}
