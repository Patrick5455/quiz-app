package com.cova.quizapp.util.constant;

import java.util.Arrays;
import java.util.List;

public interface SecurityConstants {

    List<String> PERMITTED_ENDPOINTS =Arrays.asList("");
    String AUTHORIZATION_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String SIGN_UP_URL = "/v1/cova/sign-up";
    int TOKEN_EXPIRATION_TIME = 900_000; // 15 minutes
    String SECRET = "JWTTokenSecret";


}
