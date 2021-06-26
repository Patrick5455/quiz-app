package com.cova.quizapp.unittest.service;

import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.service.IUserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    IUserService userService;

   static CreateUserRequest createUserRequest;

    @BeforeEach
    void setUp() {
    }

    @BeforeAll
    static void init(){
        createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstname("Test");
        createUserRequest.setLastname("User");
        createUserRequest.setEmail("testuser@email.com");
        createUserRequest.setUsername("test-user");
        createUserRequest.setPassword("test-password");
        createUserRequest.setConfirmPassword("test-password");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("create user happy path test")
    void createUserHappyPathTest(){
       assertEquals(userService.createUser(createUserRequest), 1);
    }
}