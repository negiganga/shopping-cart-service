package com.ganga.www.sample.springboot.service.dataaccess.mongodb;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductCategory;
import com.ganga.www.sample.springboot.service.common.model.user.Account;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.dataaccess.exception.ProductAccessDbException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * This class would be responsible for maintaining pooled connections
 * to the mongo DB which is a non structured DB to store product information.
 *
 * Ad products have no predefined schema and they can have fields in any fashion,
 * hence, using a NoSQL DB fits well here.
 */
public class MongoDbClient {

  private static final Map<String, Product> SAMPLE_DATA =
      Map.of(
          "productID100",
          new Product(
            "productID100",
              "product name",
              "product description",
              new Price(3.2, CurrencyCode.USD),
              "https://thumbnail-file-location.url.com",
              new ArrayList<>(
                  Arrays.asList(
                      "https://main-image-file-location.com",
                      "https://first-image-file-location.com")),
              new ProductCategory("category ID", "Household", "Household stuff umbrella"),
              ZonedDateTime.now(),
              new Account()));

  /**
   * Mocked function.
   *
   * This method is responsible to fetch the product from the DB using the ID.
   *
   * @param productId : Unique ID to identify the product in DB.
   *
   * @return Product
   */
  public Product fetchProduct(String productId) throws ProductAccessDbException {
    if (SAMPLE_DATA.containsKey(productId)) {
      return SAMPLE_DATA.get(productId);
    }
    throw new ProductAccessDbException(SystemEvent.NO_MATCHING_PRODUCT,
      new StringBuilder(productId).append(" not found in DB.").toString());
  }
}
