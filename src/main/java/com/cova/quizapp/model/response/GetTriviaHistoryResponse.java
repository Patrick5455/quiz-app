package com.cova.quizapp.model.response;

import com.cova.quizapp.model.entity.TriviaHistory;
import lombok.Data;
import java.util.List;

@Data
public class GetTriviaHistoryResponse implements TriviaResponse {
    List<TriviaHistory> trivia_history_list ;
    private int total_trivia_taken;
    private String username;
}
