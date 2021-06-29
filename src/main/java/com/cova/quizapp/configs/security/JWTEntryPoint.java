package com.cova.quizapp.configs.security;

import com.cova.quizapp.model.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;

@Configuration
public class JWTEntryPoint implements AuthenticationEntryPoint {


    @Qualifier("handlerExceptionResolver")
    private   HandlerExceptionResolver resolver;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ServletServerHttpResponse res = new ServletServerHttpResponse(response);
        res.getBody().write(new ObjectMapper().writeValueAsBytes(ApiResponse.errorMessage("incorrect login details", 400)));
        response.setStatus(400);
        resolver.resolveException(request, response,  null, e);
    }
}
