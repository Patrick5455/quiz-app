package com.cova.quizapp.configs.security;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.cova.quizapp.util.constant.SecurityConstants.*;

@Component
public class JWTAuthenticationVerificationFilter extends BasicAuthenticationFilter {

    @Autowired
    public JWTAuthenticationVerificationFilter(AuthenticationManager authMnger){
        super(authMnger);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)  throws IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION_HEADER);
        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req){
        String token = req.getHeader(AUTHORIZATION_HEADER);
        if(token != null){
            String user = JWT.require(HMAC512(SECRET.getBytes())).build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            if(user != null){
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }



}
