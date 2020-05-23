package com.example.webshop.entities;

import com.example.webshop.security.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRoleEnum userRole;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppUser> appUsers;

}
