package com.cova.quizapp.service;

import com.cova.quizapp.exception.UserSignUpOrSignInException;
import com.cova.quizapp.model.request.CreateUserRequest;

public interface IUserService {

    /***
     *
     * @param createUserRequest a class containing data needed to create the user in the DB
     * @return the generated id of the new user
     */
    long createUser(CreateUserRequest createUserRequest) throws UserSignUpOrSignInException;
}
