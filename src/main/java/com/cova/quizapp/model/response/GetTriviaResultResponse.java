package com.cova.quizapp.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize
public class GetTriviaResultResponse {
    private String num_of_answered_questions;
    private int total_score;
    private int total_failed;
    private int total_passed;
    private String difficulty_level;
    private String performance;


    void updateTriviaSession(String num_of_answered_questions, int total_score, int total_failed, int total_passed){
        setNum_of_answered_questions(num_of_answered_questions);
        setTotal_score(total_score);
        setTotal_failed(total_failed);
        setTotal_passed(total_passed);
    }

    enum Performance{
        EXCELLENT(0.7),
        AVERAGE(0.59),
        POOR(0.39),
        FAILED(0.0);

        private final double requiredScore;
        Performance(double requiredScore){ this.requiredScore = requiredScore; }
        public double getRequiredScore() { return requiredScore;}

        static Performance getPerformance(double percentScore){
            Performance performance;
            if(percentScore >= 0.7) {
                performance = EXCELLENT;
                return performance;
            }
            if(percentScore >=0.59){
                performance = AVERAGE;
                return performance;
            }

            if(percentScore >= 0.39){
                performance = AVERAGE;
                return performance;
            }

            if(percentScore < 0.39){
                performance = AVERAGE;
                return performance;
            }
            return null;
        }

    }
}
