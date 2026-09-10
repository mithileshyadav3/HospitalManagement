package com.customer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Viewmessage>CustomerNotFound(CustomerNotFoundException ex){
        Viewmessage viewmessage=new Viewmessage(ex.getMessage(),ex.getHttpStatus());
        return new  ResponseEntity<>(viewmessage,ex.getHttpStatus());
    }
    @ExceptionHandler(InvalidCredential.class)
    public ResponseEntity<Viewmessage>CustomerBadCredentials(InvalidCredential ex){
        Viewmessage viewmessage=new Viewmessage(ex.getMessage(),ex.getHttpStatus());
        return new  ResponseEntity<>(viewmessage,ex.getHttpStatus());
    }
}
