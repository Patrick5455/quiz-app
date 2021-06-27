package com.cova.quizapp.service;

import com.cova.quizapp.model.entity.Trivia;

import com.cova.quizapp.model.response.TriviaResponse;

public interface ITriviaService {

     /**
      * @param level trivia difficulty level
      */
     void startTrivia (Trivia.DifficultyLevel level);

     /**
      * @param level trivia difficulty level
      * @return an instance of GetNewTriviaResponse
      * **/
     TriviaResponse getTrivia(Trivia.DifficultyLevel level);

     /**
      * @return an instance of GetTriviaHistoryResponse
      * **/
     TriviaResponse getTriviaHistory();

     /**
      * @param level difficulty level
      * @return an instance of GetTriviaResultResponse
      **/
     TriviaResponse endTrivia(Trivia.DifficultyLevel level);

     /**
      * @return result of trivia(s) taken
      * **/
     TriviaResponse getTriviaResult();





}
