package com.ganga.www.sample.springboot.service.common.model.product;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ProductMetadata {
  private String productId;
  private String name;
  private String description;
  private Price price;
  private String thumbnailImageUrl;
  private double rating;
  private ZonedDateTime deliveryDateTime;

  public ProductMetadata(String productId, String name, String description,
                         Price price, String thumbnailImageUrl, double rating,
                         ZonedDateTime deliveryDateTime) {
    this.productId = productId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.rating = rating;
    this.deliveryDateTime = deliveryDateTime;
  }
}
