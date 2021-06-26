package com.cova.quizapp.controller;

import com.cova.quizapp.service.IUserService;
import com.cova.quizapp.serviceimpl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova/")
@Data
public class UserController {

    IUserService userService;

    @Autowired
    private UserController(UserServiceImpl userService){
        this.userService = userService;
    }



}
