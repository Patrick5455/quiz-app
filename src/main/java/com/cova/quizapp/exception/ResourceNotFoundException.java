package com.cova.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource does not exist ")
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Throwable throwable){
        super("Something went wrong while fetching resource. Likely Cause: Resource does not exist", throwable);
    }
}
