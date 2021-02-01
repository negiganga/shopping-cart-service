package com.ganga.www.sample.springboot.service.dataaccess;

import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.dataaccess.exception.ProductAccessDbException;

import java.util.List;

public interface ProductEngine {
  /**
   * This method will fetch product from the DB using product ID.
   *
   * @param productId Unique ID of the product present in DB.
   *
   * @return Product as requested by the user.
   */
  Product fetchProduct(String productId) throws ProductAccessDbException;

  ProductReview fetchProductReview(String productId);

  List<ProductMetadata> fetchTopSearchProductsMetadata(List<String> productIds);
}
