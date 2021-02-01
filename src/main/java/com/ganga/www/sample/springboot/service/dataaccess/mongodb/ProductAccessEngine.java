package com.ganga.www.sample.springboot.service.dataaccess.mongodb;

import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.dataaccess.ProductEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.ProductAccessDbException;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.PostgreSqlDbClient;

import java.util.List;

public class ProductAccessEngine implements ProductEngine {
  private MongoDbClient mongoDbClient;
  private PostgreSqlDbClient postgreSqlDbClient;

  public ProductAccessEngine(final MongoDbClient mongoDbClient,
                             final PostgreSqlDbClient postgreSqlDbClient) {
    this.mongoDbClient = mongoDbClient;
    this.postgreSqlDbClient = postgreSqlDbClient;
  }

  @Override
  public Product fetchProduct(final String productId)
    throws ProductAccessDbException {
    return mongoDbClient.fetchProduct(productId);
  }

  @Override
  public ProductReview fetchProductReview(String productId) {
    return postgreSqlDbClient.fetchProductReview(productId);
  }

  @Override
  public List<ProductMetadata> fetchTopSearchProductsMetadata(List<String> productIds) {
    return postgreSqlDbClient.fetchTopSearchProductsMetadata(productIds);
  }
}
