package com.cova.quizapp.controller;

import com.cova.quizapp.model.entity.Trivia;
import com.cova.quizapp.model.response.TriviaResponse;
import com.cova.quizapp.service.ITriviaService;
import com.cova.quizapp.serviceimpl.TriviaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cova/trivia")
@Slf4j
public class TriviaController {

    private ITriviaService triviaService;

    @Autowired
    public TriviaController(TriviaServiceImpl triviaService){
        this.triviaService = triviaService;
    }

    @GetMapping("/start")
    public ResponseEntity<?> startTrivia(@RequestParam("level")String selectedLevel){
        Trivia.DifficultyLevel level;
        log.info("level: {}",selectedLevel);
        if(selectedLevel == null){
          level  = Trivia.DifficultyLevel.EASY;
        }else {
          level  = Trivia.DifficultyLevel.valueOf(selectedLevel);
        }
        triviaService.startTrivia(level);
        TriviaResponse trivia = triviaService.getNextTrivia(level);
        return ResponseEntity.ok().body(trivia);
    }

    @GetMapping("/next-trivia")
    public ResponseEntity<?> nextTrivia(@RequestParam("level") String level,
                                        @RequestParam("prev_qos_id") int id,
                                        @RequestParam("prev_ans") String answer){

      TriviaResponse triviaResponse =  triviaService.getNextTrivia(Trivia.DifficultyLevel.valueOf(level), answer, id);
        return ResponseEntity.ok().body(triviaResponse);
    }

    @GetMapping("/end-trivia")
    public ResponseEntity<?> endTrivia(@RequestParam("level") String level){
        TriviaResponse response = triviaService.endTrivia(Trivia.DifficultyLevel.valueOf(level));
        return ResponseEntity.ok(response);
    }

}
