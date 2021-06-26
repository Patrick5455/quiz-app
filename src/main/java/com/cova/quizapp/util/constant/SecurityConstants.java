package com.cova.quizapp.util.constant;

import java.util.Arrays;
import java.util.List;

public interface SecurityConstants {

    List<String> PERMITTED_ENDPOINTS =Arrays.asList("");
    String AUTHORIZATION_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String SIGN_UP_URL = "/v1/cova/sign-up";
    Long TOKEN_EXPIRATION_TIME = 172_800L; //2 days
    String SECRET = "JWTTokenSecret";


}
