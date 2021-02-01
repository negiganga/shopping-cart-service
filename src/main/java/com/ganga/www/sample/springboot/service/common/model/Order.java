package com.ganga.www.sample.springboot.service.common.model;

import com.ganga.www.sample.springboot.service.common.constants.OrderStatus;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Order {
  private String orderId;
  private OrderStatus orderStatus;
  private ZonedDateTime orderDate;
  private String userId;
  // Should be product rather.
  private List<ProductMetadata> products;
  private Price totalPrice;
  // Should also carry payment information.

  public Order(String orderId, OrderStatus orderStatus,
               ZonedDateTime orderDate, String userId,
               List<ProductMetadata> products, Price totalPrice) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.orderDate = orderDate;
    this.products = products;
    this.userId = userId;
    this.totalPrice = totalPrice;
  }
}
