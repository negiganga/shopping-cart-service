package com.ganga.www.sample.springboot.service.domain.service.search;

import com.ganga.www.sample.springboot.service.api.model.GetProductResponse;
import com.ganga.www.sample.springboot.service.api.model.GetProductsMetadataResponse;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.dataaccess.SearchEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;
import com.ganga.www.sample.springboot.service.domain.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductSearch implements SearchService {

  private SearchFacade searchFacade;
  private SearchEngine searchEngine;

  public ProductSearch(final SearchFacade searchFacade,
                       final SearchEngine searchEngine) {
    this.searchFacade = searchFacade;
    this.searchEngine = searchEngine;
  }

  @Override
  public GetProductsMetadataResponse searchProducts(final String name,
                                                    final int limit,
                                                    final String nextPageIndex) {
    final GetProductsMetadataResponse response = new GetProductsMetadataResponse();
    List<ProductMetadata> productMetadataList =
      findProductsMetadataInCache(name, limit, nextPageIndex);
    // Found cached response.
    if (!CollectionUtils.isEmpty(productMetadataList)) {
      response.setProductsMetadata(new ArrayList<>(productMetadataList));
      return response;
    }

    final List<String> productIds = searchEngine.getProductIds(name);
    if (CollectionUtils.isEmpty(productIds)) {
      // We may return predefined product metadata list.
    }
    productMetadataList = searchFacade.getProductMetadataList(productIds);
    response.setProductsMetadata(new ArrayList<>(productMetadataList));
    return response;
  }

  private List<ProductMetadata> findProductsMetadataInCache(final String name,
                                                            final int limit,
                                                            final String nextPageIndex) {
    try {
      return searchFacade.getCachedProductsMetadata(name, limit, nextPageIndex);
    } catch (RedisAccessException e) {
      log.error("Event={}, no product metadata found for keyword {}.",
        e.getEvent().getId(), name);
      return (List<ProductMetadata>) CollectionUtils.EMPTY_COLLECTION;
    }
  }

  @Override
  public GetProductResponse getProduct(String productId) {
    final GetProductResponse response = new GetProductResponse();
    Product product = searchFacade.getProduct(productId);
    if (product == null) {
      return null;
    }
    response.setProduct(product);
    ProductReview productReview = searchFacade.getProductReview(productId);
    if (productReview != null) {
      response.setProductReview(productReview);
    }
    return response;
  }
}
