package com.example.webshop.security;

import com.example.webshop.security.exceptionhandling.AccessDeniedHandlerImplementation;
import com.example.webshop.security.exceptionhandling.AuthenticationEntryPointImplementation;
import com.example.webshop.security.exceptionhandling.AuthenticationFailureHandlerImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.example.webshop.security.exceptionhandling.AuthenticationSuccessHandlerImplementation;

@Configuration
public class Beans {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandlerImplementation accessDeniedHandler() {
        return new AccessDeniedHandlerImplementation();
    }

    @Bean
    AuthenticationEntryPointImplementation authenticationEntryPoint() {
        return new AuthenticationEntryPointImplementation();
    }

    @Bean
    AuthenticationFailureHandlerImplementation authenticationFailureHandler(){
        return new AuthenticationFailureHandlerImplementation();
    }

//    @Bean
//    AuthenticationSuccessHandlerImplementation successHandler(){
//        return new AuthenticationSuccessHandlerImplementation();
//    }

}