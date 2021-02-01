package com.ganga.www.sample.springboot.service.common.model.product;

import com.ganga.www.sample.springboot.service.common.model.user.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ElectronicsProduct extends Product {
  private Integer availableItemCount;

  public ElectronicsProduct(String productId, String name, String description, Price price,
                            String thumbnailImageUrl, ArrayList<String> imageUrls,
                            ProductCategory productCategory, ZonedDateTime deliveryDateTime,
                            Account sellerAccount, Integer availableItemCount) {
    super(productId, name, description, price, thumbnailImageUrl, imageUrls,
      productCategory, deliveryDateTime, sellerAccount);

    this.availableItemCount = availableItemCount;
  }
}
