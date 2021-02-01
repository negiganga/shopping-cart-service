package com.ganga.www.sample.springboot.service.common.model.product;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ProductReview {
  private String reviewId;
  private double rating;
  private String review;
  private String productId;
  private ZonedDateTime reviewDateTime;
  private String userId;
  private String userName;

  public ProductReview(String reviewId,
                       double rating,
                       String review,
                       String productId,
                       ZonedDateTime reviewDateTime,
                       String userId,
                       String userName) {
    this.reviewId = reviewId;
    this.rating = rating;
    this.review = review;
    this.productId = productId;
    this.reviewDateTime = reviewDateTime;
    this.userId = userId;
    this.userName = userName;
  }
}
