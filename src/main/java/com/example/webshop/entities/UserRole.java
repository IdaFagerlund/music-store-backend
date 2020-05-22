package com.example.webshop.entities;

import com.example.webshop.security.UserRoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppUser> appUsers;

    public UserRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

}
