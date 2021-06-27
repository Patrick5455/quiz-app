package com.cova.quizapp.controller;

import com.auth0.jwt.JWT;
import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.model.response.ApiResponse;
import com.cova.quizapp.service.IUserService;
import com.cova.quizapp.serviceimpl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.cova.quizapp.util.constant.SecurityConstants.SECRET;


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

    @GetMapping("")
    public String home(){
        return "welcome to cova trivia";
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signupNewUser( @RequestBody CreateUserRequest createUserRequest){

        try {
            long lastInsertedId = userService.createUser(createUserRequest);
            log.info("new user with id {} successfully created", lastInsertedId);
        } catch (Exception e){
           return ResponseEntity.badRequest().body("sign up was not successful "+e.getMessage());
            }
        return ResponseEntity.ok().body("user successfully created");
    }

    public void  logout(HttpServletRequest request){


        JWT.require(HMAC512(SECRET.getBytes())).acceptExpiresAt(0L);

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
