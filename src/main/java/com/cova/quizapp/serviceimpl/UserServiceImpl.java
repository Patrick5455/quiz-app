package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.UserRepository;
import com.cova.quizapp.exception.UserSignUpOrSignInException;
import com.cova.quizapp.model.entity.AppUser;
import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.service.IUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Data
@Slf4j
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Override
    public long createUser(CreateUserRequest param) throws UserSignUpOrSignInException {
        if(param.getPassword() == null || param.getConfirm_password() == null){
            log.error("please fill all required fields for password");
            throw new UserSignUpOrSignInException("please fill all required fields for password");
        }
        if(!param.getPassword().trim().equals(param.getConfirm_password().trim())){
            log.error("password fields and confirm passwords field do not match");
            throw new UserSignUpOrSignInException("password fields and confirm passwords field do not match");
        }

        if(!userRepository.findByUsername(param.getUsername()).isPresent()) {
            AppUser newUser = AppUser.builder()
                    .firstname(param.getFirstname())
                    .lastname(param.getLastname())
                    .email(param.getEmail())
                    .username(param.getUsername())
                    .password(encoder.encode(param.getPassword()))
                    .isActive(1)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            long insertedId = userRepository.save(newUser).getId();
            log.info("new user with id {} saved successfully", insertedId);
            return insertedId;
        }
        else {
            throw new UserSignUpOrSignInException("that username already exists - please try another one");
        }
    }

    @Override
    public AppUser getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) auth.getPrincipal();
        AppUser appUser = userRepository.findByUsername(username)
                .orElse(AppUser.builder().build());
        log.info("logged in user: {}", appUser);
        return appUser;
    }
}
