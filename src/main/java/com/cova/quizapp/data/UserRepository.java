package com.cova.quizapp.data;

import com.cova.quizapp.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query(value = FIND_BY_USERNAME, nativeQuery = true)
    Optional<AppUser> findByUsername(String username);

    String FIND_BY_USERNAME  = "SELECT * FROM app_user WHERE username=?1";

}
