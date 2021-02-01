package com.ganga.www.sample.springboot.service.common.model.user;

import com.ganga.www.sample.springboot.service.common.model.Order;
import com.ganga.www.sample.springboot.service.common.model.ShoppingCart;
import lombok.Data;

@Data
public abstract class User {
  private String userId;
  private ShoppingCart shoppingCart;
  private Order order;
}
