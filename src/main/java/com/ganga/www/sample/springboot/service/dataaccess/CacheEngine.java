package com.ganga.www.sample.springboot.service.dataaccess;

import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;

import java.util.List;

public interface CacheEngine<T> {
  /**
   * This method is responsible for fetching the cached products/product summary,
   * based on the top searched results as user has not provided any search element.
   */
  T getProduct(String productId) throws RedisAccessException;

  /**
   * This method is responsible for fetching the cached products/product summary,
   * based on the user's entered search element and page index if available.
   */
  List<T> getProducts(String name, int limit, String nextPageIndex)
    throws RedisAccessException;
}
