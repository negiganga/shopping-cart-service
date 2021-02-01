package com.ganga.www.sample.springboot.service.dataaccess.cache.redis;

import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;

import java.util.List;

public class ProductMetadataCacheEngine implements CacheEngine<ProductMetadata> {

  private final RedisClient redisClient;

  public ProductMetadataCacheEngine(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  @Override
  public ProductMetadata getProduct(String productId) {
    throw new UnsupportedOperationException("Out of scope");
  }

  @Override
  public List<ProductMetadata> getProducts(String name, int limit, String nextPageIndex)
    throws RedisAccessException {
    return redisClient.getProductsMetadata(name, limit, nextPageIndex);
  }
}
