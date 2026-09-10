package com.appointment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> getResourceNotFound(ResourceNotFoundException ex){
        ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),ex.getHttpStatus());

        return new ResponseEntity<>(errorMessage,ex.getHttpStatus());


    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public  ResponseEntity<ErrorMessage>getInvaidCredentials(InvalidCredentialsException ex){
        ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),ex.getHttpStatus());

        return new ResponseEntity<>(errorMessage,ex.getHttpStatus());
    }

}
