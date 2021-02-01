package com.ganga.www.sample.springboot.service.dataaccess.postgresql;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import com.ganga.www.sample.springboot.service.common.constants.OrderStatus;
import com.ganga.www.sample.springboot.service.common.model.Item;
import com.ganga.www.sample.springboot.service.common.model.Order;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.dataaccess.exception.PostgresqlAccessException;
import org.apache.commons.collections.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * All the structured data is going to be stored in SQL DB,
 * so that we can perform join queries on them, and get the benefits of ACID properties
 * while making transactions.
 *
 * MOCKED Class - This can also be splitted into separate classes and
 * more refined interfaces to access SQL client.
 */
public class PostgreSqlDbClient {

  private static final Map<String, ProductReview> SAMPLE_PRODUCTS_REVIEW_MAP =
    Map.of(
      "reviewID1",
      new ProductReview(
        "reviewID1", 3.5, "User's review",
        "product ID", ZonedDateTime.now(), "userID1", "Test Name"
        ));

  private static final Map<String, ProductMetadata> SAMPLE_PRODUCTS_METADATA_MAP =
    Map.of(
      "productID111",
      new ProductMetadata(
        "productID111",
        "product name",
        "product description",
        new Price(13.2, CurrencyCode.USD),
        "https://thumbnail-file-location.url.com",
        2.5,
        ZonedDateTime.now()),
      "product ID",
      new ProductMetadata(
        "product ID",
        "product name 2",
        "product description 2",
        new Price(12.2, CurrencyCode.USD),
        "https://thumbnail-file-location2.url.com",
        5.5,
        ZonedDateTime.now()));

  private static final Map<String, List<String>> SHOPPING_CART_ITEMS_MAP =
      new HashMap<>() {
        {
          put("ShoppingCart1", List.of("Item11", "Item12"));
          put("ShoppingCart2", List.of("Item21", "Item22", "Item23"));
          put("ShoppingCart3", List.of("Item34", "Item31", "Item32", "Item34"));
        }
      };

  private static final Map<String, Item> ITEMS_MAP =
      new HashMap<>() {
        {
          put(
              "Item11",
              new Item(
                  "Item11",
                  "ProductId1",
                  1,
                  new Price(40.0, CurrencyCode.USD),
                  ZonedDateTime.now()));
          put(
            "Item12",
            new Item(
              "Item12",
              "ProductId2",
              4,
              new Price(10.0, CurrencyCode.USD),
              ZonedDateTime.now()));
          put(
            "Item21",
            new Item(
              "Item21",
              "ProductId3",
              2,
              new Price(44.0, CurrencyCode.USD),
              ZonedDateTime.now()));
          put(
            "Item31",
            new Item(
              "Item31",
              "ProductId4",
              1,
              new Price(43.0, CurrencyCode.USD),
              ZonedDateTime.now()));
        }
      };

  private static final Map<String, Order> ORDER_MAP =
      new HashMap<>() {
        {
          put(
              "OrderId1",
              new Order(
                  "OrderId1",
                  OrderStatus.COMPLETED,
                  ZonedDateTime.now(),
                  "UserId1",
                  Arrays.asList(
                      new ProductMetadata(
                          "new product ID",
                          "product name",
                          "product description",
                          new Price(13.2, CurrencyCode.USD),
                          "https://thumbnail-file-location.url.com",
                          2.5,
                          ZonedDateTime.now())),
                  new Price(400.0, CurrencyCode.USD)));
        }
      };

  public ProductReview fetchProductReview(String productId) {
    return SAMPLE_PRODUCTS_REVIEW_MAP.getOrDefault(productId, null);
  }

  public List<ProductMetadata> fetchTopSearchProductsMetadata(List<String> productIds) {
    return productIds.stream()
      .filter(productId -> SAMPLE_PRODUCTS_METADATA_MAP.containsKey(productId))
      .map(productId -> SAMPLE_PRODUCTS_METADATA_MAP.get(productId))
      .collect(Collectors.toList());
  }

  public List<Item> fetchItemsShoppingCart(String shoppingCartId)
    throws PostgresqlAccessException {
    final List<String> itemIds = SHOPPING_CART_ITEMS_MAP.get(shoppingCartId);
    if (CollectionUtils.isEmpty(itemIds)) {
      throw new PostgresqlAccessException(
        SystemEvent.POSTGRESQL_ITEM_ID_NOT_FOUND, " No items found for this cart.");
    }
    return itemIds.stream()
      .filter(itemId -> ITEMS_MAP.containsKey(itemId))
      .map(itemId -> ITEMS_MAP.get(itemId))
      .collect(Collectors.toList());
  }

  public void addItemToCart(String shoppingCartId, Item item) {
    List<String> items =
      SHOPPING_CART_ITEMS_MAP.getOrDefault(shoppingCartId, new ArrayList<>());
    items.add(item.getItemId());
    ITEMS_MAP.put(item.getItemId(), item);
  }

  public void removeItemFromCart(String shoppingCartId, String itemId) {
    ITEMS_MAP.remove(itemId);
    SHOPPING_CART_ITEMS_MAP.remove(shoppingCartId);
  }

  public void incrementItemQuantity(String itemId, int count) {
    Item item = ITEMS_MAP.get(itemId);
    item.setQuantity(item.getQuantity() + count);
  }

  public void decrementItemQuantity(String itemId, int count)
    throws PostgresqlAccessException {
    Item item = ITEMS_MAP.get(itemId);
    if (item.getQuantity() > count) {
      item.setQuantity(item.getQuantity() - count);
    } else {
      throw new PostgresqlAccessException(SystemEvent.POSTGRESQL_PRODUCT_ID,
        "Invalid operation.");
    }
  }

  public Order getOrder(String orderId) {
    return ORDER_MAP.get(orderId);
  }
}
