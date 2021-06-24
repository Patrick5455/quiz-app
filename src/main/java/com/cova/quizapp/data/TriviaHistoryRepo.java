package com.cova.quizapp.data;

import com.cova.quizapp.model.TriviaHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaHistoryRepo extends JpaRepository<TriviaHistory, Long> {
}
