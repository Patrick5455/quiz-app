package com.cova.quizapp.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova/trivia-hist")
@Data
public class TriviaHistoryController {

    private ITriviaHistoryService triviaHistoryService;

    @Autowired
    public TriviaHistoryController(TriviaHistoryServiceImpl triviaHistoryService){
        this.triviaHistoryService = triviaHistoryService;
    }
}
