package com.cova.quizapp.configs.security;

import com.cova.quizapp.serviceimpl.UserDetailsServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.cova.quizapp.util.constant.SecurityConstants.SIGN_UP_URL;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@Data
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    private BCryptPasswordEncoder encoder;

    private JWTEntryPoint jwtEntryPoint;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder encoder, JWTEntryPoint jwt){
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.jwtEntryPoint = jwt;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL)
                .permitAll()
                .antMatchers(HttpMethod.GET ,"/v1/cova")
                .permitAll()
                .antMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManagerBean()))
                .addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(STATELESS);

        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
       return super.authenticationManagerBean();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

}
