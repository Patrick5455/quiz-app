package com.cova.quizapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaHistoryRepository extends JpaRepository<TriviaHistoryRepository, Long> {
}
