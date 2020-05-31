package com.example.webshop.security;

import com.example.webshop.entities.AppUser;
import com.example.webshop.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final AppUserService appUserService;

    // Runs on login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByUsername(username);

        Collection<? extends GrantedAuthority> authorities = appUser.getUserRoles()
                .stream().map(userRole -> new SimpleGrantedAuthority(userRole.getUserRole().toString()))
                .collect(Collectors.toList());

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

}
