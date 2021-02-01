package com.ganga.www.sample.springboot.service.domain.service;

import com.ganga.www.sample.springboot.service.api.model.GetShoppingCartResponse;
import com.ganga.www.sample.springboot.service.common.constants.ColorType;
import com.ganga.www.sample.springboot.service.common.constants.SizeType;

public interface IShoppingCartService {

  GetShoppingCartResponse getShoppingCart(String shoppingCartId);

  void addItemShoppingCart(String shoppingCartId, String productId,
                           int quantity, SizeType size, ColorType color);

  void removeItemShoppingCart(String shoppingCartId, String itemId);

  void incrementItemQuantityShoppingCart(String itemId, int count);

  void decrementItemQuantityShoppingCart(String itemId, int count);
}
