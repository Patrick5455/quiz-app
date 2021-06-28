package com.cova.quizapp.model.response;

import com.cova.quizapp.model.entity.TriviaHistory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.util.List;

@Data
@JsonDeserialize
public class GetTriviaHistoryResponse implements TriviaResponse {
    List<TriviaHistory> trivia_history_list ;
    private int total_trivia_taken;
    private String username;
}
