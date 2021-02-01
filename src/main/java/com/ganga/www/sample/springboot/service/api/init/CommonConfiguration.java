package com.ganga.www.sample.springboot.service.api.init;

import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.SearchEngine;
import com.ganga.www.sample.springboot.service.dataaccess.cache.redis.ProductCacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.cache.redis.ProductMetadataCacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.cache.redis.RedisClient;
import com.ganga.www.sample.springboot.service.dataaccess.elasticsearch.ElasticSearchClient;
import com.ganga.www.sample.springboot.service.dataaccess.elasticsearch.ElasticSearchEngine;
import com.ganga.www.sample.springboot.service.dataaccess.mongodb.MongoDbClient;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.PostgreSqlDbClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

  @Bean
  ResponseGenerator responseGenerator() {
    return new ResponseGenerator();
  }

  @Bean
  SearchEngine searchEngine(ElasticSearchClient elasticSearchClient) {
    return new ElasticSearchEngine(elasticSearchClient);
  }

  @Bean
  ElasticSearchClient elasticSearchClient() {
    return new ElasticSearchClient();
  }

  @Bean
  MongoDbClient mongoDbClient() {
    return new MongoDbClient();
  }

  @Bean
  CacheEngine<ProductMetadata> productMetadataCacheEngine(
    RedisClient redisClient) {
    return new ProductMetadataCacheEngine(redisClient);
  }

  @Bean
  CacheEngine<Product> productCacheEngine(RedisClient redisClient) {
    return new ProductCacheEngine(redisClient);
  }

  @Bean
  RedisClient redisClient() {
    return new RedisClient();
  }

  @Bean
  PostgreSqlDbClient postgreSqlDbClient() {
    return new PostgreSqlDbClient();
  }
}
