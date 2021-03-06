package com.example.webshop.entities;

import com.example.webshop.models.ProductReviewRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter @Setter
public class ProductReview {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 1200)
    private String comment;
    private int stars;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public ProductReview() {
        this.timeCreatedUTC = Instant.now();
    }

    public ProductReview(ProductReviewRequestModel productReviewModel) {
        this.comment = productReviewModel.getComment();
        this.stars = productReviewModel.getStars();
        this.timeCreatedUTC = Instant.now();
    }

}
