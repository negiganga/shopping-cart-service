package com.ganga.www.sample.springboot.service.domain.mock;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductCategory;
import com.ganga.www.sample.springboot.service.common.model.user.Account;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductMock {

  public static Product init() {
    return new Product(
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
      new Account());
  }
}
