package com.cova.quizapp.data;

import com.cova.quizapp.model.entity.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Long> {

   long countAllByDifficultyLevel(Trivia.DifficultyLevel level);

    @Query(value = FIND_TRIVIA_ANSWER_BY_TRIVIA_QUESTION_ID, nativeQuery = true)
    String findTriviaAnswer(int triviaId);




    String COUNT_TOTAL_AVAILABLE_QUESTIONS_BY_DIFFICULTY_LEVEL =
            "SELECT COUNT(*) FROM trivia WHERE difficulty_level = ?1";

    String FIND_TRIVIA_ANSWER_BY_TRIVIA_QUESTION_ID =
            "SELECT answer FROM trivia WHERE  id = ?1";
}
