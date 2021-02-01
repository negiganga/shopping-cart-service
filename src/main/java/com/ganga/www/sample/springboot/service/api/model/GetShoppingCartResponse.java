package com.ganga.www.sample.springboot.service.api.model;

import com.ganga.www.sample.springboot.service.common.model.ShoppingCart;
import lombok.Data;

@Data
public class GetShoppingCartResponse extends BaseResponse {
  private ShoppingCart shoppingCart;
}
