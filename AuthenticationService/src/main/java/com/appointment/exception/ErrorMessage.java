package com.appointment.exception;



import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
     private  String message;
     private HttpStatus httpStatus;
     private LocalDateTime dateTime;

    public ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
        this.dateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
