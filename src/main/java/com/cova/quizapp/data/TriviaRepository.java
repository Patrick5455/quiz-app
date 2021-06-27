package com.cova.quizapp.data;

import com.cova.quizapp.model.entity.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Long> {

   long countAllByDifficultyLevel(Trivia.DifficultyLevel level);

   @Query(value = GET_ALL_ID_FOR_QUESTION_DIFFICULTY, nativeQuery = true)
   Optional<List<Long>> getTriviaIds(String level);

   Optional<List<Trivia>> findByDifficultyLevel(Trivia.DifficultyLevel level);

   Optional<Trivia> findByDifficultyLevelAndId(Trivia.DifficultyLevel level, long qosId);

    @Query(value = FIND_TRIVIA_ANSWER_BY_TRIVIA_QUESTION_ID, nativeQuery = true)
    String findTriviaAnswer(int triviaId);


    /**
     *NATIVE SQL QUERIES
     */
    String GET_ALL_ID_FOR_QUESTION_DIFFICULTY =
            "SELECT id FROM trivia WHERE difficulty_level=:level";
    String FIND_TRIVIA_ANSWER_BY_TRIVIA_QUESTION_ID =
            "SELECT answer FROM trivia WHERE  id = ?1";
}
