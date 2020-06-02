package com.example.webshop.controllers;

import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.models.AppUserResponseModel;
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
    public ResponseEntity<Void> register(@RequestBody AppUserRequestModel appUser) {
        System.out.println("trying to register");
        appUserService.register(appUser);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    public ResponseEntity<AppUserResponseModel> getAllDataForUser(Principal principal) {
        return ResponseEntity.status(200).body(appUserService.getAllDataForUser(principal));
    }

    @PatchMapping("/")
    public ResponseEntity<AppUserResponseModel> patchUser(Principal principal, @RequestBody AppUserRequestModel appUser) {
        return ResponseEntity.status(200).body(appUserService.patchUser(principal, appUser));
    }

}
