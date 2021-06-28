package com.cova.quizapp.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@JsonDeserialize
@Slf4j
public class GetTriviaResultResponse implements TriviaResponse{
    private int num_of_answered_trivia;
    private int num_passed_trivia;
    private int num_failed_trivia;
    private double total_score;
    private String difficulty_level;
    private String performance;


   public void updateTriviaSession(int num_of_answered_questions, int num_passed_trivia,
                             int num_failed_trivia, double total_score){
        setNum_of_answered_trivia(num_of_answered_questions);
        setNum_passed_trivia(num_passed_trivia);
        setNum_failed_trivia(num_failed_trivia);
        setTotal_score(total_score);
    }

   public void updateTriviaSession(int num_of_answered_questions, int total_passed,
                             int total_failed, int total_score,  String performance){
        setNum_of_answered_trivia(num_of_answered_questions);
        setNum_passed_trivia(total_passed);
        setNum_failed_trivia(total_failed);
        setPerformance(performance);
        setTotal_score(total_score);
    }

    public Performance calculatePerformance(double percentScore){
        return Performance.getPerformance(percentScore);
    }

    public enum Performance{
        EXCELLENT(0.70),
        AVERAGE(0.59),
        POOR(0.39),
        FAILED(0.0),
        NO_GRADE (0.0);

        private final double requiredScore;
        Performance(double requiredScore){ this.requiredScore = requiredScore; }
        public double getRequiredScore() { return requiredScore;}
       public static Performance getPerformance(double percentScore){
            log.info("calc");
            if(percentScore >= EXCELLENT.requiredScore) {
                return EXCELLENT;
            }
            if(percentScore >= AVERAGE.requiredScore){
              return AVERAGE;
            }

            if(percentScore >= POOR.requiredScore){
                return POOR;
            }

            if(percentScore < POOR.requiredScore){
                return FAILED;
            }
            return NO_GRADE;
        }

    }
}
