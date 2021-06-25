package com.cova.quizapp.unittest.controller;

import com.cova.quizapp.service.ITriviaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TriviaController.class)
class TriviaControllerTest {

    @Autowired
     MockMvc mockMvc;

    @MockBean
     ITriviaService triviaService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}