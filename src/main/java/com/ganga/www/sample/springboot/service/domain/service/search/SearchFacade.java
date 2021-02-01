package com.ganga.www.sample.springboot.service.domain.service.search;

import com.ganga.www.sample.springboot.service.api.exception.SearchProductException;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.ProductEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.ProductAccessDbException;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SearchFacade {
  private ProductEngine productEngine;
  private CacheEngine<ProductMetadata> productMetadataCacheEngine;
  private CacheEngine<Product> productCacheEngine;

  public SearchFacade(
      final ProductEngine productEngine,
      final CacheEngine<ProductMetadata> productMetadataCacheEngine,
      final CacheEngine<Product> productCacheEngine) {
    this.productEngine = productEngine;
    this.productMetadataCacheEngine = productMetadataCacheEngine;
    this.productCacheEngine = productCacheEngine;
  }

  public List<ProductMetadata> getCachedProductsMetadata(
    String name, int limit, String nextPageIndex) throws RedisAccessException {
    try {
      return productMetadataCacheEngine.getProducts(name, limit, nextPageIndex);
    } catch (RedisAccessException e) {
      log.error("EventId={}. Cache miss for search keyword {}.",
        e.getEvent().getId(), name);
      throw e;
    }
  }

  public Product getProduct(final String productId) {
    try {
      // Fetch from the cache first.
      return productCacheEngine.getProduct(productId);
    } catch (RedisAccessException e) {
      log.error("EventId={}. Cache miss for product id {}.",
        e.getEvent().getId(), productId);
      try {
        // Fetch from the DB as fallback option.
        return productEngine.fetchProduct(productId);
      } catch (ProductAccessDbException ex) {
        // Wrap in custom exception.
        throw new SearchProductException(e.getEvent(), e.getMessage(), e);
      }
    }
  }

  public ProductReview getProductReview(final String productId) {
    return productEngine.fetchProductReview(productId);
  }

  /**
   * Fetch top products based on may be ratings, personalization, frequent search ..
   */
  public List<ProductMetadata> getProductMetadataList(final List<String> productIds) {
    return productEngine.fetchTopSearchProductsMetadata(productIds);
  }
}
