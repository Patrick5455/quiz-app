package com.cova.quizapp.controller;

import com.cova.quizapp.service.ITriviaHistoryService;
import com.cova.quizapp.serviceimpl.TriviaHistoryServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/trivia-hist")
@Data
public class TriviaHistoryController {

    private ITriviaHistoryService triviaHistoryService;

    @Autowired
    public TriviaHistoryController(TriviaHistoryServiceImpl triviaHistoryService){
        this.triviaHistoryService = triviaHistoryService;
    }
}
