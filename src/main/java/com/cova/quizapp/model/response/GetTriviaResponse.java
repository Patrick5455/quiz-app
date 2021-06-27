package com.cova.quizapp.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonDeserialize
@Data

public class GetTriviaResponse {
    private String question;
    private String level;
}
