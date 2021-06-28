package com.cova.quizapp.configs.security;

import com.auth0.jwt.JWT;
import com.cova.quizapp.model.request.CreateUserRequest;
import com.cova.quizapp.model.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.cova.quizapp.util.constant.SecurityConstants.*;

@Component
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public JWTAuthenticationFilter(AuthenticationManager authMgr) {
        super(authMgr);
        authenticationManager = authMgr;
        setFilterProcessesUrl("/v1/cova/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            LoginRequest credentials = new ObjectMapper().readValue(
                    request.getInputStream(), LoginRequest.class);
            try {
               return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                credentials.getUsername(),
                                credentials.getPassword(),
                                new ArrayList<>())
                );
            }
            catch (Exception e){
                throw new UsernameNotFoundException("incorrect login details");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void successfulAuthentication(HttpServletRequest req,
                                         HttpServletResponse res,
                                         FilterChain chain,
                                         Authentication auth) throws IOException, ServletException{


        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX+token);
        res.setStatus(200);

    }



}
