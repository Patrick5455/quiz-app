package com.cova.quizapp.controller;

import com.cova.quizapp.service.ITriviaService;
import com.cova.quizapp.serviceimpl.TriviaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova/trivia")
public class TriviaController {

    private ITriviaService triviaService;

    @Autowired
    public TriviaController(TriviaServiceImpl triviaService){
        this.triviaService = triviaService;
    }


}
