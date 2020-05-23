package com.example.webshop.controllers;

import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.models.UserDataResponseModel;
import com.example.webshop.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AppUserRequestModel appUserModel) {
        appUserService.register(appUserModel);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    public ResponseEntity<UserDataResponseModel> getAllDataForUser(Principal principal) {
        return ResponseEntity.status(200).body(appUserService.getAllDataForUser(principal));
    }

    @PatchMapping("/")
    public ResponseEntity<String> patchUser(Principal principal, @RequestBody AppUserRequestModel appUserModel) {
        return ResponseEntity.status(200).body(appUserService.patchUser(principal, appUserModel));
    }

}
