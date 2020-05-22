package com.example.webshop.repositories;

import com.example.webshop.entities.UserRole;
import com.example.webshop.security.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByUserRole(UserRoleEnum userRole);
}
