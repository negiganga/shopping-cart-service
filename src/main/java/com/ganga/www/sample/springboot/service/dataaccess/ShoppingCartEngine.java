package com.ganga.www.sample.springboot.service.dataaccess;

import com.ganga.www.sample.springboot.service.common.model.Item;
import com.ganga.www.sample.springboot.service.dataaccess.exception.PostgresqlAccessException;

import java.util.List;

public interface ShoppingCartEngine {
  List<Item> getShoppingCartItems(String shoppingCartId) throws PostgresqlAccessException;

  void incrementItemQuantity(String itemId, int count);

  void decrementItemQuantity(String itemId, int count) throws PostgresqlAccessException;

  void removeItem(String shoppingCartId, String itemId);

  void addItem(String shoppingCartId, Item item);
}
