package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.UserRepository;
import com.cova.quizapp.model.entity.AppUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Data
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("incorrect username") {});
        log.info("login request user found");
        return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
    }
}
