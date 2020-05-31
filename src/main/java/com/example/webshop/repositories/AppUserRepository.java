package com.example.webshop.repositories;

import com.example.webshop.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
    AppUser getByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
