package com.admin.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends  RuntimeException{
         private HttpStatus httpStatus;

    public BadRequestException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
