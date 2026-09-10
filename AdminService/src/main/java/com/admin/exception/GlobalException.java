package com.admin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ViewMessage>ResourceNotFound(ResourceNotFoundException ex){
             ViewMessage viewMessage=new ViewMessage(ex.getMessage(),ex.getHttpStatus());
        return new ResponseEntity<>(viewMessage,ex.getHttpStatus()) ;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ViewMessage>BadrequestNotFound(BadRequestException ex){
        ViewMessage viewMessage=new ViewMessage(ex.getMessage(),ex.getHttpStatus());
        return new ResponseEntity<>(viewMessage,ex.getHttpStatus()) ;
    }
}
