package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.UserRepository;
import com.cova.quizapp.model.persistence.AppUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        AppUser appUser = userRepository.findByUsername(username);
        if(appUser == null){
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
        log.info("user details successfully loaded");
        return userDetails;
    }
}
