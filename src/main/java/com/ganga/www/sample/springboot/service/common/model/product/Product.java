package com.ganga.www.sample.springboot.service.common.model.product;

import com.ganga.www.sample.springboot.service.common.model.user.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Product {
  protected String productId;
  protected String name;
  protected String description;
  protected Price price;
  protected String thumbnailImageUrl;
  protected ArrayList<String> imageUrls;
  protected ProductCategory productCategory;
  protected ZonedDateTime deliveryDateTime;

  private Account sellerAccount;

  public Product(String productId, String name, String description, Price price,
                 String thumbnailImageUrl, ArrayList<String> imageUrls,
                 ProductCategory productCategory, ZonedDateTime deliveryDateTime,
                 Account sellerAccount) {
    this.productId = productId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.imageUrls = imageUrls;
    this.productCategory = productCategory;
    this.deliveryDateTime = deliveryDateTime;
    this.sellerAccount = sellerAccount;
  }
}
