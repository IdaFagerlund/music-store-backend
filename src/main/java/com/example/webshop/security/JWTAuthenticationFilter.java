package com.example.webshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.webshop.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.webshop.security.SecurityConstants.*;

// Methods that run on login
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
        } catch (Exception e) {
            throw new InsufficientAuthenticationException("");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // Getting these with the help of the user details service
        List<String> userRoles = authResult.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withClaim("authorities", userRoles)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        response.addHeader("Access-Control-Expose-Headers", "Authorization");

        Map<String,Object> responseJson = new HashMap<>();
        responseJson.put("username", ((User) authResult.getPrincipal()).getUsername());
        responseJson.put("authorities", userRoles);
        OutputStream out = response.getOutputStream();
        new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(out, responseJson);
        out.flush();
    }

}
