package com.ganga.www.sample.springboot.service.domain.mock;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import com.ganga.www.sample.springboot.service.common.model.product.Price;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;

import java.time.ZonedDateTime;

public class ProductMetadataMock {

  public static ProductMetadata init() {
    return new ProductMetadata(
      "productID111",
      "product name",
      "product description",
      new Price(13.2, CurrencyCode.USD),
      "https://thumbnail-file-location.url.com",
      2.5,
      ZonedDateTime.now());
  }
}
