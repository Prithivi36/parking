package com.rentalparking.paraking.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
