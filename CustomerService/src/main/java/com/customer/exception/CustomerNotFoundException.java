package com.customer.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends  RuntimeException {
    private   HttpStatus httpStatus;

    public CustomerNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
