package com.example.webshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webshop.entities.AppUser;
import com.example.webshop.security.exceptionhandling.AuthenticationFailureHandlerImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.webshop.security.SecurityConstants.*;

//import com.example.webshop.security.exceptionhandling.AuthenticationSuccessHandlerImplementation;

// Methods that run on login that creates and returns a JWT
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    //private AuthenticationSuccessHandlerImplementation authenticationSuccessHandler;
    private AuthenticationFailureHandlerImplementation authenticationFailureHandler;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   //AuthenticationSuccessHandlerImplementation authenticationSuccessHandler,
                                   AuthenticationFailureHandlerImplementation authenticationFailureHandler) {
        this.authenticationManager = authenticationManager;
        //this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        setFilterProcessesUrl("/users/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AppUser credentials = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(), credentials.getPassword(), new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // Getting these from the user details service
        List<String> userRoles = authResult.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withClaim("roles", userRoles)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

//    @Override
//    protected AuthenticationSuccessHandler getSuccessHandler() {
//        return authenticationSuccessHandler;
//    }

    @Override
    protected AuthenticationFailureHandler getFailureHandler() {
        return authenticationFailureHandler;
    }

}
