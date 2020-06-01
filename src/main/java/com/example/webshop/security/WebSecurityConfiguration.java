package com.example.webshop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplementation userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/users/accesstest/all").permitAll()
                .antMatchers(HttpMethod.POST, "/users/accesstest/post").permitAll()
                .antMatchers(HttpMethod.GET, "/users/accesstest/user").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/users/accesstest/admin").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.GET, "/users/").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/users/").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/products/all/light").permitAll()
                .antMatchers(HttpMethod.GET, "/products/all/full").permitAll()
                .antMatchers(HttpMethod.POST, "/products/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/products/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/products/").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/product-orders/").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/product-orders/").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/product-reviews/{productId}").permitAll()
                .antMatchers(HttpMethod.POST, "/product-reviews/{productId}").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/product-reviews/{productId}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/product-reviews/{productId}").hasRole("USER")

                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
