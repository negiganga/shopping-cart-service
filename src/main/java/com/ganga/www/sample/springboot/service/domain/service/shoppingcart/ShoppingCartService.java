package com.ganga.www.sample.springboot.service.domain.service.shoppingcart;

import com.ganga.www.sample.springboot.service.api.exception.ShoppingCartException;
import com.ganga.www.sample.springboot.service.api.model.GetShoppingCartResponse;
import com.ganga.www.sample.springboot.service.common.constants.ColorType;
import com.ganga.www.sample.springboot.service.common.constants.SizeType;
import com.ganga.www.sample.springboot.service.common.model.Item;
import com.ganga.www.sample.springboot.service.common.model.ShoppingCart;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.dataaccess.exception.PostgresqlAccessException;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.ShoppingCartAccessEngine;
import com.ganga.www.sample.springboot.service.domain.service.IShoppingCartService;
import com.ganga.www.sample.springboot.service.domain.service.search.SearchFacade;

import java.util.Objects;
import java.util.UUID;

public class ShoppingCartService implements IShoppingCartService {

  private final ShoppingCartAccessEngine shoppingCartAccessEngine;
  private final SearchFacade searchFacade;

  public ShoppingCartService(
    final ShoppingCartAccessEngine shoppingCartAccessEngine,
    final SearchFacade searchFacade) {
    this.shoppingCartAccessEngine = shoppingCartAccessEngine;
    this.searchFacade = searchFacade;
  }

  /**
   * Assumed lots of boundary cases, exception handling.
   */
  @Override
  public GetShoppingCartResponse getShoppingCart(String shoppingCartId) {
    // 1. Fetch shopping cart from the cache.
    // 2. If not present then pull from DB.
    final GetShoppingCartResponse response = new GetShoppingCartResponse();
    final ShoppingCart shoppingCart = new ShoppingCart();
    try {
      shoppingCart.setItems(shoppingCartAccessEngine.getShoppingCartItems(shoppingCartId));
    } catch (PostgresqlAccessException e) {
      throw new ShoppingCartException(e.getEvent(), e.getMessage(), e);
    }
    shoppingCart.setId(shoppingCartId);
    shoppingCart.setTotal(shoppingCart.getItems()
      .stream()
      .filter(Objects::nonNull)
      .mapToDouble(item -> item.getSingleProductPrice().getAmount())
      .sum());
    response.setShoppingCart(shoppingCart);
    return response;
  }

  /**
   * Assumed lots of boundary cases, exception handling.
   */
  @Override
  public void addItemShoppingCart(String shoppingCartId, String productId,
                                  int quantity, SizeType size, ColorType color) {
    // 1. Invalidate shopping cart cache
    // 2. Validate color, size, quantity available in inventory.
    // 3. Now, add item to the cart.
    Product product = searchFacade.getProduct(productId);
    Item item = new Item(UUID.randomUUID().toString(), productId, quantity,
      product.getPrice(), product.getDeliveryDateTime());
    shoppingCartAccessEngine.addItem(shoppingCartId, item);
  }

  /**
   * Assumed lots of boundary cases, exception handling.
   */
  @Override
  public void removeItemShoppingCart(String shoppingCartId, String itemId) {
    // 1. Invalidate shopping cart cache
    shoppingCartAccessEngine.removeItem(shoppingCartId, itemId);
  }

  /**
   * Assumed lots of boundary cases, exception handling.
   */
  @Override
  public void incrementItemQuantityShoppingCart(String itemId, int count) {
    // 1. Invalidate shopping cart cache
    shoppingCartAccessEngine.incrementItemQuantity(itemId, count);
  }

  /**
   * Assumed lots of boundary cases, exception handling.
   */
  @Override
  public void decrementItemQuantityShoppingCart(String itemId, int count) {
    // 1. Invalidate shopping cart cache
    try {
      shoppingCartAccessEngine.decrementItemQuantity(itemId, count);
    } catch (PostgresqlAccessException e) {
      throw new ShoppingCartException(e.getEvent(), e.getMessage(), e);
    }
  }
}
