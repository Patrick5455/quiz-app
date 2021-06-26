package com.cova.quizapp.unittest.controller;

import com.cova.quizapp.service.IAppUserService;
import com.cova.quizapp.serviceimpl.AppUserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova/")
@Data
public class UserController {

    IAppUserService userService;

    @Autowired
    private UserController(AppUserServiceImpl userService){
        this.userService = userService;
    }



}
