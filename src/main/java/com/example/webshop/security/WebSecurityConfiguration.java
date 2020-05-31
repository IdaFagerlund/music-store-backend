package com.example.webshop.security;

import com.example.webshop.security.exceptionhandling.AccessDeniedHandlerImplementation;
import com.example.webshop.security.exceptionhandling.AuthenticationEntryPointImplementation;
import com.example.webshop.security.exceptionhandling.AuthenticationFailureHandlerImplementation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.example.webshop.security.exceptionhandling.AuthenticationSuccessHandlerImplementation;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplementation userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AccessDeniedHandlerImplementation accessDeniedHandler;
    private AuthenticationEntryPointImplementation authenticationEntryPoint;
    private AuthenticationFailureHandlerImplementation authenticationFailureHandler;
    //private AuthenticationSuccessHandlerImplementation authenticationSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                //.accessDeniedHandler(accessDeniedHandler)
                //.authenticationEntryPoint(authenticationEntryPoint)
                .and()

                .authorizeRequests()

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
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), authenticationFailureHandler))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }

//    @Bean
//    AuthenticationFilter authenticationFilter() throws Exception {
//        final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationSuccessHandler(successHandler());
//        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
//        return filter;
//    }




    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

}


// http
//         .cors().and().csrf().disable()
//         .authorizeRequests()
//
//         .antMatchers(HttpMethod.POST, "/users/register").permitAll()
//         .antMatchers(HttpMethod.GET, "/users/").hasRole("USER")
//         .antMatchers(HttpMethod.PATCH, "/users/").hasRole("USER")
//
//         .antMatchers(HttpMethod.GET, "/products/all/light").permitAll()
//         .antMatchers(HttpMethod.GET, "/products/all/full").permitAll()
//         .antMatchers(HttpMethod.POST, "/products/").hasRole("ADMIN")
//         .antMatchers(HttpMethod.PATCH, "/products/{id}").hasRole("ADMIN")
//         .antMatchers(HttpMethod.DELETE, "/products/").hasRole("ADMIN")
//
//         .antMatchers(HttpMethod.GET, "/product-orders/").hasRole("USER")
//         .antMatchers(HttpMethod.POST, "/product-orders/").hasRole("USER")
//
//         .antMatchers(HttpMethod.GET, "/product-reviews/{productId}").permitAll()
//         .antMatchers(HttpMethod.POST, "/product-reviews/{productId}").hasRole("USER")
//         .antMatchers(HttpMethod.PATCH, "/product-reviews/{productId}").hasRole("USER")
//         .antMatchers(HttpMethod.DELETE, "/product-reviews/{productId}").hasRole("USER")
//
//         .anyRequest().authenticated()
//         .and()
//         .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//         .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);