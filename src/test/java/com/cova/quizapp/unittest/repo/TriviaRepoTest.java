package com.cova.quizapp.unittest.repo;


import com.cova.quizapp.data.TriviaRepository;
import com.cova.quizapp.model.entity.Trivia;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
@Sql(scripts = {"classpath:/test-schema.sql",
        "classpath:/test-data.sql" })
public class TriviaRepoTest {

    @Autowired
    TriviaRepository triviaRepository;


    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void we_can_get_count_of_trivias_by_their_difficulty_level() {
      long count =  triviaRepository.countAllByDifficultyLevel(Trivia.DifficultyLevel.HARD);
      Assertions.assertEquals(count, 2);
    }

    @Test
    void we_can_get_trivia_ids_by_difficulty_level() {
       List<Long> triviaIds =  triviaRepository.getTriviaIds("HARD").orElse(null);
       Assertions.assertNotNull(triviaIds);
    }

    @Test
    void we_can_get_list_of_trivia_by_their_difficulty_level() {
       List<Trivia> hardTrivia =  triviaRepository.findByDifficultyLevel(Trivia.DifficultyLevel.HARD).orElse(null);
       Assertions.assertNotNull(hardTrivia);
    }

    @Test
    void find_by_difficulty_level_and_id() {
       Trivia trivia = triviaRepository.findByDifficultyLevelAndId(Trivia.DifficultyLevel.EASY, 1).orElse(null);
       Assertions.assertNotNull(trivia);
    }

    @Test
    void find_trivia_answer_by_id() {
        String answer = triviaRepository.findTriviaAnswer(1);
        Assertions.assertNotNull(answer);
    }



}
