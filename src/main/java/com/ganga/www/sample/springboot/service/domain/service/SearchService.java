package com.ganga.www.sample.springboot.service.domain.service;

import com.ganga.www.sample.springboot.service.api.model.GetProductResponse;
import com.ganga.www.sample.springboot.service.api.model.GetProductsMetadataResponse;

public interface SearchService {

  GetProductsMetadataResponse searchProducts(String name, int limit, String nextPageIndex);

  GetProductResponse getProduct(String productId);
}
