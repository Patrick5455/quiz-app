package com.cova.quizapp.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import javax.validation.constraints.*;

@JsonDeserialize
@Data
public class CreateUserRequest {

    @NotBlank
    @NotNull
    private String firstname;
    @NotBlank
    @NotNull
    private String lastname;
    @NotBlank
    @NotNull
    private String username;
    @Email
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @NotNull
    private String confirmPassword;

}
