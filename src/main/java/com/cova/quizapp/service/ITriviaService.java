package com.cova.quizapp.service;

import com.cova.quizapp.model.entity.Trivia;
import com.cova.quizapp.model.response.GetTriviaHistoryResponse;
import com.cova.quizapp.model.response.GetTriviaResponse;
import com.cova.quizapp.model.response.GetTriviaResultResponse;

public interface ITriviaService {

     /**
      * @param level trivia difficulty level
      */
     void startTrivia (Trivia.DifficultyLevel level);

     /**
      * @param level trivia difficulty level
      * @return an instance of GetTriviaResponse
      * **/
     GetTriviaResponse getTrivia(Trivia.DifficultyLevel level);

     /**
      * @return an instance of GetTriviaHistoryResponse
      * **/
     GetTriviaHistoryResponse getTriviaHistory();

     void endTrivia();

     /**
      * @return result of trivia(s) taken
      * **/
     GetTriviaResultResponse getTriviaResult();





}
