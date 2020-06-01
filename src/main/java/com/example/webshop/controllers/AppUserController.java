package com.example.webshop.controllers;

import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.models.UserDataResponseModel;
import com.example.webshop.models.errors.RegisterErrorResponseModel;
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
    public ResponseEntity<UserDataResponseModel> getAllDataForUser(Principal principal) {
        return ResponseEntity.status(200).body(appUserService.getAllDataForUser(principal));
    }

    @PatchMapping("/")
    public ResponseEntity<String> patchUser(Principal principal, @RequestBody AppUserRequestModel appUser) {
        return ResponseEntity.status(200).body(appUserService.patchUser(principal, appUser));
    }





    @GetMapping("/accesstest/all")
    public ResponseEntity<RegisterErrorResponseModel> asd() {
        return ResponseEntity.status(200).body(new RegisterErrorResponseModel("a", "b", "c"));
    }
    @GetMapping("/accesstest/user")
    public  ResponseEntity<RegisterErrorResponseModel> asasdd(Principal principal) {
        System.out.println(principal.getName());
        return ResponseEntity.status(200).body(new RegisterErrorResponseModel("a", "b", "c"));
    }
    @GetMapping("/accesstest/admin")
    public  ResponseEntity<RegisterErrorResponseModel> asasdasdd() {
        return ResponseEntity.status(200).body(new RegisterErrorResponseModel("a", "b", "c"));
    }

    @PostMapping("/accesstest/post")
    public ResponseEntity<RegisterErrorResponseModel> asda() {
        System.out.println("running post");
        return ResponseEntity.status(200).body(new RegisterErrorResponseModel("a", "b", "c"));
    }

}
