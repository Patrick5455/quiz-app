package com.cova.quizapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonDeserialize
@Data
public class CreateUserRequest {
   // @JsonProperty
    private String username;

    private String password;

    private String confirmPassword;

}
