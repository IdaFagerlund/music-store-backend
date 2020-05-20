package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.appUserRepository = appUserRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }


    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void save(AppUser appUser) {
        //appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

}
