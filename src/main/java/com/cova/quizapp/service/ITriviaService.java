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
      * @param previousAnswers answer to previously answered trivia - used to update the map of user answered trivia
      * @param previousTriviaId id of previously answered trivia
      * @return an instance of GetNewTriviaResponse
      * **/
     TriviaResponse getNextTrivia(Trivia.DifficultyLevel level, Object previousAnswers, int previousTriviaId);

     /**
      * @param level trivia difficulty level
      * @return an instance of GetNewTriviaResponse
      * **/
     TriviaResponse getNextTrivia(Trivia.DifficultyLevel level);

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
      * @return the total number of questions answered right by client
      **/
     int getTotalCorrectQuestions();






}
