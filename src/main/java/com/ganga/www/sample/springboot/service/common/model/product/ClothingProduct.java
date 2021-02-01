package com.ganga.www.sample.springboot.service.common.model.product;

import com.ganga.www.sample.springboot.service.common.constants.SizeType;
import com.ganga.www.sample.springboot.service.common.model.user.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
public class ClothingProduct extends Product {
  private Map<SizeType, Integer> availableSizesCount;

  public ClothingProduct(String productId, String name, String description, Price price,
                         String thumbnailImageUrl, ArrayList<String> imageUrls,
                         ProductCategory productCategory, ZonedDateTime deliveryDateTime,
                         Account sellerAccount, Map<SizeType, Integer> availableSizesCount) {
    super(productId, name, description, price, thumbnailImageUrl, imageUrls,
      productCategory, deliveryDateTime, sellerAccount);

    this.availableSizesCount = availableSizesCount;
  }
}
