package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.models.UserDataResponseModel;
import com.example.webshop.repositories.AppUserRepository;
import com.example.webshop.repositories.UserRoleRepository;
import com.example.webshop.security.UserRoleEnum;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UtilService utilService;

    public void register(AppUserRequestModel appUserModel) {
        utilService.validateThatFieldsAreNotNullOrEmpty(
                appUserModel.getUsername(), appUserModel.getPassword(), appUserModel.getEmail()
        );
        validateEmail(appUserModel.getEmail());
        validateUsername(appUserModel.getUsername());

        AppUser appUser = new AppUser(
                appUserModel.getUsername(),
                bCryptPasswordEncoder.encode(appUserModel.getPassword()),
                appUserModel.getEmail()
        );
        appUser.getUserRoles().add(userRoleRepository.findByUserRole(UserRoleEnum.ROLE_USER));
        appUserRepository.save(appUser);
    }

    public UserDataResponseModel getAllDataForUser(Principal principal) {
        return new UserDataResponseModel(findByUsername(principal.getName()));
    }

    public String patchUser(Principal principal, AppUserRequestModel appUserModel) {
        AppUser appUser = findByUsername(principal.getName());
        if(appUser.getUsername() != null) {
            appUser.setUsername(appUserModel.getUsername());
        }
        if(appUser.getPassword() != null) {
            appUser.setPassword(bCryptPasswordEncoder.encode(appUserModel.getPassword()));
        }
        appUserRepository.save(appUser);
        return appUser.getUsername();
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Can not access an unexisting user"));
    }

    private void validateEmail(String email) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isPresent()) {
            throw new ValidationException(409, "Unavailable email");
        }
        if(!EmailValidator.getInstance().isValid(email)) {
            throw new ValidationException(400, "Email does not look like a valid email address");
        }
    }

    private void validateUsername(String username) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if(appUser.isPresent()) {
            throw new ValidationException(409, "Unavailable username");
        }
    }

}
