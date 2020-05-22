package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.entities.UserRole;
import com.example.webshop.models.UserDataResponseModel;
import com.example.webshop.repositories.AppUserRepository;
import com.example.webshop.repositories.UserRoleRepository;
import com.example.webshop.security.UserRoleEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(AppUserRepository appUserRepository, UserRoleRepository userRoleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        UserRole userRole = userRoleRepository.findByUserRole(UserRoleEnum.ROLE_USER);
        userRole.getAppUsers().add(appUser);
        appUser.getUserRoles().add(userRole);
        //System.out.println(userRole.toString());
        //appUser.getUserRoles().add();
        appUserRepository.save(appUser);
    }

    public UserDataResponseModel getUserData(Principal principal) {
        return new UserDataResponseModel(appUserRepository.findByUsername(principal.getName()));
    }

}
