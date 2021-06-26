package com.cova.quizapp.unittest.controller;

import com.cova.quizapp.service.ITriviaHistoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TriviaHistoryController.class)
class TriviaHistoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
     ITriviaHistoryService triviaHistoryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

}