package com.ganga.www.sample.springboot.service.dataaccess.cache.redis;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductCategory;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.user.Account;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;
import org.apache.commons.lang.StringUtils;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Maintaining pool of connection to a distributed cache like Redis for quick lookup,
 * or accessing pre-processed data.
 *
 * Mocked
 */
public class RedisClient {

  private static final String DEFAULT_PRODUCT_SEARCH_RESULT = "top searched result";

  private static final Map<String, Product> SAMPLE_PRODUCTS_MAP =
    Map.of(
      "productID30",
      new Product(
        "productID30",
        "product name",
        "product description",
        new Price(13.2, CurrencyCode.USD),
        "https://thumbnail-file-location.url.com",
        new ArrayList<>(
          Arrays.asList(
            "https://main-image-file-location.com",
            "https://first-image-file-location.com")),
        new ProductCategory("category ID", "Household", "Household stuff umbrella"),
        ZonedDateTime.now(),
        new Account()));

  private static final Map<String, Map<String, List<ProductMetadata>>> SAMPLE_PRODUCTS_METADATA_MAP =
      Map.of(
          "keyword 1",
          Map.of(
              "page 1",
              Arrays.asList(
                  new ProductMetadata(
                      "productID1",
                      "product name",
                      "product description",
                      new Price(13.2, CurrencyCode.USD),
                      "https://thumbnail-file-location.url.com",
                      2.5,
                      ZonedDateTime.now()),
                  new ProductMetadata(
                      "productID2",
                      "product name 2",
                      "product description 2",
                      new Price(12.2, CurrencyCode.USD),
                      "https://thumbnail-file-location2.url.com",
                      5.5,
                      ZonedDateTime.now()))),
          DEFAULT_PRODUCT_SEARCH_RESULT,
          Map.of(
              "Page 1",
              Arrays.asList(
                  new ProductMetadata(
                      "productID5",
                      "product name",
                      "product description",
                      new Price(13.2, CurrencyCode.USD),
                      "https://thumbnail-file-location.url.com",
                      2.5,
                      ZonedDateTime.now()),
                  new ProductMetadata(
                      "productID6",
                      "product name 2",
                      "product description 2",
                      new Price(12.2, CurrencyCode.USD),
                      "https://thumbnail-file-location2.url.com",
                      5.5,
                      ZonedDateTime.now())),
            "Page 2",
            Arrays.asList(
              new ProductMetadata(
                "productID3",
                "product name 3",
                "product description 3",
                new Price(43.2, CurrencyCode.USD),
                "https://thumbnail-file-location3.url.com",
                4.5,
                ZonedDateTime.now()),
              new ProductMetadata(
                "productID4",
                "product name 4",
                "product description 4",
                new Price(22.2, CurrencyCode.USD),
                "https://thumbnail-file-location4.url.com",
                1.5,
                ZonedDateTime.now()))));

  /**
   *  This method is responsible for returning products metadata based on the search keyword,
   *  if in case this string is empty then we return pre-processed top searched products list.
   */
  public List<ProductMetadata> getProductsMetadata(final String searchKeyword,
                                                   int limit, String nextPageIndex)
    throws RedisAccessException {
    // Assuming: paginated values will be returned.
    if (StringUtils.isBlank(searchKeyword)) {
      return SAMPLE_PRODUCTS_METADATA_MAP.get(DEFAULT_PRODUCT_SEARCH_RESULT)
        .values().iterator().next();
    } else if (SAMPLE_PRODUCTS_METADATA_MAP.containsKey(searchKeyword)) {
      return SAMPLE_PRODUCTS_METADATA_MAP.get(searchKeyword)
        .values().iterator().next();
    }
    throw new RedisAccessException(SystemEvent.REDIS_CACHE_MISS_PRODUCT_METADATA,
      new StringBuilder(searchKeyword).append(" encountered cache miss.").toString());
  }

  public Product getProduct(String productId) throws RedisAccessException {
    if (SAMPLE_PRODUCTS_MAP.containsKey(productId)) {
      return SAMPLE_PRODUCTS_MAP.get(productId);
    }
    throw new RedisAccessException(SystemEvent.REDIS_CACHE_MISS_PRODUCT_METADATA,
      new StringBuilder(productId).append(" product id encountered cache miss.").toString());
  }
}
