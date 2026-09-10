package com.customer.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredential  extends  RuntimeException{
           private HttpStatus httpStatus;

    public InvalidCredential(String message) {
          super(message);
        this.httpStatus =HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
