package com.ganga.www.sample.springboot.service.common.model.product;

import lombok.Data;

/**
 * Details about the category to which a product belongs like
 * Coffee comes under beverages category etc.
 */
@Data
public class ProductCategory {
  // The ID is predefined and are limited.
  private String id;
  private String name;
  private String description;

  public ProductCategory(String id,
                         String name,
                         String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
