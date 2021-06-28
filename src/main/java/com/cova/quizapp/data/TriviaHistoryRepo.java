package com.cova.quizapp.data;

import com.cova.quizapp.model.entity.AppUser;
import com.cova.quizapp.model.entity.TriviaHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriviaHistoryRepo extends JpaRepository<TriviaHistory, Long> {

    Optional<List<TriviaHistory>> findAllByAppUser(AppUser appUser);
}
