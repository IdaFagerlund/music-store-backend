package com.example.webshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
public class AppUser {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "appUser", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductReview> productReviews;
    @OneToMany(mappedBy = "appUser", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductOrder> productOrders;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_user_role",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id")
    )
    private Set<UserRole> userRoles;

    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.productReviews = new ArrayList<>();
        this.productOrders = new ArrayList<>();
        this.userRoles = new HashSet<>();
    }

}
