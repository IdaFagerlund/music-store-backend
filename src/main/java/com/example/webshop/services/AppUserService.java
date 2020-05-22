package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.models.AppUserRequestModel;
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

    public void register(AppUserRequestModel appUserModel) {
        AppUser appUser = new AppUser(
                appUserModel.getUsername(),
                bCryptPasswordEncoder.encode(appUserModel.getPassword()),
                appUserModel.getEmail()
        );
        appUser.getUserRoles().add(userRoleRepository.findByUserRole(UserRoleEnum.ROLE_USER));
        appUserRepository.save(appUser);
    }

    public AppUser getAppUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    public UserDataResponseModel getUserData(Principal principal) {
        return new UserDataResponseModel(appUserRepository.findByUsername(principal.getName()));
    }

}
