package com.ganga.www.sample.springboot.service.common.model;

import com.ganga.www.sample.springboot.service.common.model.product.Price;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Item {
  private String itemId;
  private String productID;
  private int quantity;
  private Price singleProductPrice;
  private ZonedDateTime expectedDeliveryDateTime;

  public Item(String itemId, String productID, int quantity,
              Price singleProductPrice, ZonedDateTime expectedDeliveryDateTime) {
    this.itemId = itemId;
    this.productID = productID;
    this.singleProductPrice = singleProductPrice;
    this.quantity = quantity;
    this.expectedDeliveryDateTime = expectedDeliveryDateTime;
  }
}
