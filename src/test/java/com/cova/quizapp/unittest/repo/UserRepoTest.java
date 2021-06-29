package com.cova.quizapp.unittest.repo;

import com.cova.quizapp.data.UserRepository;
import com.cova.quizapp.model.entity.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
@Sql(scripts = {"classpath:/test-schema.sql",
        "classpath:/test-data.sql" })
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    static AppUser appUser1;
    static AppUser appUser2;
    static AppUser appUser3;

    @BeforeAll
    static void beforeAll() {
        appUser1 = new AppUser();
        appUser1.setFirstname("Test");
        appUser1.setLastname("User");
        appUser1.setEmail("testuser@testmail.com");
        appUser1.setUsername("alpha");
        appUser1.setPassword("test-password");
        appUser1.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        appUser1.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        appUser2 = new AppUser();
        appUser2.setFirstname("Test");
        appUser2.setLastname("User2");
        appUser2.setEmail("testuser2@testmail.com");
        appUser2.setUsername("beta");
        appUser2.setPassword("test-password");
        appUser2.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        appUser2.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        appUser3 = new AppUser();
        appUser3.setFirstname("Test");
        appUser3.setLastname("User3");
        appUser3.setEmail("testuser3@testmail.com");
        appUser3.setUsername("zee");
        appUser3.setPassword("test-password");
        appUser3.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        appUser3.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    }

    @Test
    @DisplayName("when_an_app_user_is_created_can_be_saved_to_the_DB_and_we_can_find_the_user_by_the_user_id")
    public void when_an_app_user_is_created_can_be_saved_to_the_DB_and_we_can_find_the_user_by_the_user_id(){
       long lastInsertedId =   userRepository.save(appUser1).getId();
       assertEquals(lastInsertedId, 1);
       log.info("user successfully inserted");
        AppUser appUser = userRepository.findByUsername("alpha").orElse(null);
        assertNotNull(appUser,"actual user from DB is null");
        log.info("user with username alpha was successfully found");

         lastInsertedId =   userRepository.save(appUser2).getId();
        assertEquals(lastInsertedId, 2);
        log.info("user successfully inserted");
        AppUser appUser2 = userRepository.findByUsername("beta").orElse(null);
        assertNotNull(appUser2,"actual user from DB is null");
        log.info("user with username alpha was successfully found");

        lastInsertedId =   userRepository.save(appUser3).getId();
        assertEquals(lastInsertedId, 3);
        log.info("user3 successfully inserted");
        AppUser appUser3 = userRepository.findByUsername("zee").orElse(null);
        assertNotNull(appUser3,"actual user from DB is null");
        log.info("user with username zee was successfully found");
    }

    @Test
    @DisplayName("we cannot_find_non_existing_app_user_by_username")
    public void cannot__find_non_existing_app_user_by_username(){
        AppUser appUser = userRepository.findByUsername("nonexistentuser").orElse(null);
        Assertions.assertNull(appUser,"user is supposed to be null, no user with username nonexistentuser");
    }


}
