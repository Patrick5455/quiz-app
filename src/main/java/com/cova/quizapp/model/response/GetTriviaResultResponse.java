package com.cova.quizapp.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class GetTriviaResultResponse implements TriviaResponse{
    private int num_of_answered_questions;
    private int total_passed;
    private int total_failed;
    private String difficulty_level;
    private String performance;


    void updateTriviaSession(int num_of_answered_questions, int total_passed, int total_failed){
        setNum_of_answered_questions(num_of_answered_questions);
        setTotal_passed(total_passed);
        setTotal_failed(total_failed);

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
            if(percentScore >= EXCELLENT.requiredScore) {
                return EXCELLENT;
            }
            if(percentScore >= AVERAGE.requiredScore){
              return AVERAGE;
            }

            if(percentScore >= POOR.requiredScore){
                return POOR;
            }

            if(percentScore < FAILED.requiredScore){
                return FAILED;
            }
            return NO_GRADE;
        }

    }
}
