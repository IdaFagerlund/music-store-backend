package com.example.webshop.entities;

import com.example.webshop.models.ProductReviewRequestModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor @Getter @Setter
public class ProductReview {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
    private int stars;
    private Instant timeCreatedUTC;
    private Instant lastTimeUpdatedUTC;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    public ProductReview(ProductReviewRequestModel productReviewModel) {
        this.comment = productReviewModel.getComment();
        this.stars = productReviewModel.getStars();
        this.timeCreatedUTC = Instant.now();
    }

}
