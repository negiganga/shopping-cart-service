package com.ganga.www.sample.springboot.service.common.monitoring;

public enum  SystemEvent implements Event {
  NO_MATCHING_PRODUCT(1, "No matching product found."),
  INVALID_SEARCH(2, "No valid search string."),
  INVALID_PRODUCT_ID(3, "Invalid product ID found."),
  REDIS_CACHE_MISS_PRODUCT_METADATA(4,
    "Redis cache is missing products metadata for the searched string."),
  REDIS_CACHE_MISS_PRODUCT(5, "Redis cache is missing the product."),
  POSTGRESQL_PRODUCT_ID(6, "Invalid product ID found."),
  POSTGRESQL_ITEM_ID_NOT_FOUND(13, "Invalid item ID found."),
  SEARCH_PRODUCTS_EXCEPTION(7,
    "Search products controller encountered an exception"),
  SEARCH_PRODUCT_EXCEPTION(8,
    "Search product controller encountered an exception"),
  SHOPPING_CART_VIEW_EXCEPTION(9,
    "Shopping cart view exception encountered."),
  ADD_ITEM_TO_SHOPPING_CART_EXCEPTION(10,
    "Add item to the cart exception."),
  INC_ITEM_IN_SHOPPING_CART_EXCEPTION(11,
    "Inc item count in the cart."),
  DEC_ITEM_IN_SHOPPING_CART_EXCEPTION(12,
    "Dec item count in the cart."),
  INVALID_REQUEST(50, "Invalid request.");

  private int id;
  private String description;

  SystemEvent(int id, String description) {
    this.id = id;
    this.description = description;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getName() {
    return name();
  }
}
