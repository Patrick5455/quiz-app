package com.cova.quizapp.unittest.repo;

import com.cova.quizapp.data.TriviaHistoryRepo;
import com.cova.quizapp.data.UserRepository;
import com.cova.quizapp.model.entity.AppUser;
import com.cova.quizapp.model.entity.TriviaHistory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
@Sql(scripts = {"classpath:/test-schema.sql",
        "classpath:/test-data.sql" })
public class TriviaHistoryRepoTest {

    @Autowired
    private TriviaHistoryRepo triviaHistoryRepo;

    @Autowired
    private static UserRepository userRepository;

    static AppUser appUser;

    @BeforeAll
    static void beforeAll() {
        appUser = userRepository.findByUsername("alpha ").orElseThrow(() -> new UsernameNotFoundException(""));
    }


    @Test
    public void find_all_trivia_answered_by_a_user(){
        Assertions.assertNotNull(appUser, "cannot use a non existent user to get trivia history");
     List<TriviaHistory> histories = triviaHistoryRepo.findAllByAppUser(appUser).orElse(null);
     Assertions.assertNotNull(histories);
    }

}
