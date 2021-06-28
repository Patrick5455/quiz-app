package com.cova.quizapp.controller;

import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.model.response.ApiResponse;
import com.cova.quizapp.service.IUserService;
import com.cova.quizapp.serviceimpl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/v1/cova")
@Slf4j
public class SessionController {

    private final IUserService userService;

    @Autowired
    private SessionController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("")
    public String home(){
        return "welcome to cova trivia";
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signupNewUser(@Valid @RequestBody CreateUserRequest createUserRequest,
                                           BindingResult validator)
    {
        if(validator.hasErrors()){
            log.info("user request invalidated");
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sign-up not successful please provide all required sign up details");
        }
        try {
            long lastInsertedId = userService.createUser(createUserRequest);
            log.info("new user with id {} successfully created", lastInsertedId);
        } catch (Exception e){
            log.error("error message: {} caused by: {}", e.getMessage(), e.getCause());
           return ResponseEntity.badRequest().body("sign up was not successful: please try again");
            }
        return ResponseEntity.ok().body("user successfully created");
    }

    @PostMapping("/logout")
    public  ResponseEntity<?> logout (HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response , authentication);
        }
        return ResponseEntity.ok(ApiResponse.response("successfully logged out", 200));
    }



}
