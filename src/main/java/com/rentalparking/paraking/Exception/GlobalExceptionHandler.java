package com.rentalparking.paraking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ErrorDetails> handleException(ApiException exception, WebRequest webRequest){
        ErrorDetails erd = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return  new ResponseEntity<>(erd, HttpStatus.BAD_REQUEST);
    }
}
