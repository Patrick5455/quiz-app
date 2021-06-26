package com.cova.quizapp.unittest.controller;


import com.cova.quizapp.controller.UserController;
import com.cova.quizapp.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
     MockMvc mockMvc;

    @MockBean
    IUserService appUserService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

}