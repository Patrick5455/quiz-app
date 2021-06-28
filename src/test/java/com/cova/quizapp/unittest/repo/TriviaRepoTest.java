package com.cova.quizapp.unittest.repo;


import com.cova.quizapp.data.TriviaRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TriviaRepoTest {


    @Autowired
    TriviaRepository triviaRepository;


}
