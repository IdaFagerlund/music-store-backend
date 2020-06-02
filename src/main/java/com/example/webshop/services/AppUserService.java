package com.example.webshop.services;

import com.example.webshop.entities.AppUser;
import com.example.webshop.exceptions.NotFoundException;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.models.AppUserResponseModel;
import com.example.webshop.models.errors.UserFieldsErrorResponseModel;
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
        UserFieldsErrorResponseModel errors = new UserFieldsErrorResponseModel(
                validateEmail(appUserModel.getEmail()),
                validateUsername(appUserModel.getUsername()),
                validatePassword(appUserModel.getPassword())
        );
        if(utilService.doesValidationErrorsExists(errors.getEmailErrorMessage(), errors.getUsernameErrorMessage(),
                errors.getPasswordErrorMessage())) {
            throw new ValidationException(errors);
        }

        AppUser newAppUser = new AppUser(
                appUserModel.getUsername(),
                bCryptPasswordEncoder.encode(appUserModel.getPassword()),
                appUserModel.getEmail()
        );
        newAppUser.getUserRoles().add(userRoleRepository.findByUserRole(UserRoleEnum.ROLE_USER));
        appUserRepository.save(newAppUser);
    }

    public AppUserResponseModel getAllDataForUser(Principal principal) {
        return new AppUserResponseModel(findByUsername(principal.getName()));
    }

    public AppUserResponseModel patchUser(Principal principal, AppUserRequestModel appUserModel) {
        AppUser appUser = findByUsername(principal.getName());
        UserFieldsErrorResponseModel errors = new UserFieldsErrorResponseModel();

        if(appUserModel.getUsername() != null) {
            errors.setUsernameErrorMessage(validateUsername(appUserModel.getUsername()));
            appUser.setUsername(appUserModel.getUsername());
        }
        if(appUserModel.getPassword() != null) {
            errors.setPasswordErrorMessage(validatePassword(appUserModel.getPassword()));
            appUser.setPassword(bCryptPasswordEncoder.encode(appUserModel.getPassword()));
        }

        if(utilService.doesValidationErrorsExists(errors.getUsernameErrorMessage(), errors.getPasswordErrorMessage())) {
            throw new ValidationException(errors);
        }

        appUserRepository.save(appUser);
        return new AppUserResponseModel(appUser);
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    private String validateEmail(String email) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(utilService.isFieldNullOrEmpty(email)) {
            return "Missing email";
        }
        else if(!EmailValidator.getInstance().isValid(email)) {
            return  "Email does not look like a valid email address";
        }
        else if(appUser.isPresent()) {
            return "Unavailable email";
        }
        return null;
    }

    private String validateUsername(String username) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if(utilService.isFieldNullOrEmpty(username)) {
            return "Missing username";
        }
        else if(appUser.isPresent()) {
            return "Unavailable username";
        }
        return null;
    }

    private String validatePassword(String password) {
        if(utilService.isFieldNullOrEmpty(password)) {
            return "Missing password";
        }
        return null;
    }

}
