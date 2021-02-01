package com.ganga.www.sample.springboot.service.dataaccess.cache.redis;

import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;

import java.util.List;

public class ProductCacheEngine implements CacheEngine<Product> {

  private final RedisClient redisClient;

  public ProductCacheEngine(final RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  @Override
  public Product getProduct(String productId) throws RedisAccessException {
    return redisClient.getProduct(productId);
  }

  @Override
  public List<Product> getProducts(String name, int limit, String nextPageIndex) {
    throw new UnsupportedOperationException("Out of scope.");
  }
}
