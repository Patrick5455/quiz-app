package com.cova.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "inappropriate arguments were provided")
public class UserSignUpOrSignInException extends RuntimeException{

    public UserSignUpOrSignInException(String message, Throwable cause){
        super(message, cause);
    }

    public UserSignUpOrSignInException(String message){
        super(message);
    }

    public UserSignUpOrSignInException(Throwable throwable){
        super("inappropriate arguments were provided", throwable);
    }
}
