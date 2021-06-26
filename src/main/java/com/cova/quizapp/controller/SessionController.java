package com.cova.quizapp.controller;

import com.cova.quizapp.exception.UserSignUpOrSignInException;
import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.service.IUserService;
import com.cova.quizapp.serviceimpl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova")
@Data
@Slf4j
public class SessionController {

    IUserService userService;


    @Autowired
    private SessionController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupNewUser(@RequestBody CreateUserRequest createUserRequest){

        try {
            long lastInsertedId = userService.createUser(createUserRequest);
            log.info("new user with id {} successfully created", lastInsertedId);
        } catch (Exception e){
           return ResponseEntity.badRequest().body("sign up was not successful "+e.getMessage());
            }
        return ResponseEntity.ok().body("user successfully created");
    }

}
