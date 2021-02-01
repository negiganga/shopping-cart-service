package com.ganga.www.sample.springboot.service.dataaccess.postgresql;

import com.ganga.www.sample.springboot.service.common.model.Item;
import com.ganga.www.sample.springboot.service.dataaccess.ShoppingCartEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.PostgresqlAccessException;

import java.util.List;

public class ShoppingCartAccessEngine implements ShoppingCartEngine {

  private final PostgreSqlDbClient postgreSqlDbClient;

  public ShoppingCartAccessEngine(final PostgreSqlDbClient postgreSqlDbClient) {
    this.postgreSqlDbClient = postgreSqlDbClient;
  }

  @Override
  public List<Item> getShoppingCartItems(String shoppingCartId)
    throws PostgresqlAccessException {
    return postgreSqlDbClient.fetchItemsShoppingCart(shoppingCartId);
  }

  @Override
  public void incrementItemQuantity(String itemId, int count) {
    postgreSqlDbClient.incrementItemQuantity(itemId, count);
  }

  @Override
  public void decrementItemQuantity(String itemId, int count)
    throws PostgresqlAccessException {
    postgreSqlDbClient.decrementItemQuantity(itemId, count);
  }

  @Override
  public void removeItem(String shoppingCartId, String itemId) {
    postgreSqlDbClient.removeItemFromCart(shoppingCartId, itemId);
  }

  @Override
  public void addItem(String shoppingCartId, Item item) {
    // 1. START TRANSACTION; Make sure you grab a lock before updating the table.
    postgreSqlDbClient.addItemToCart(shoppingCartId, item);
  }
}
