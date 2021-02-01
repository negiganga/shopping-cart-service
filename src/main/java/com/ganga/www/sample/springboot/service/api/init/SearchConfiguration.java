package com.ganga.www.sample.springboot.service.api.init;

import com.ganga.www.sample.springboot.service.api.controller.SearchController;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.api.validator.Validator;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.ProductEngine;
import com.ganga.www.sample.springboot.service.dataaccess.SearchEngine;
import com.ganga.www.sample.springboot.service.dataaccess.mongodb.MongoDbClient;
import com.ganga.www.sample.springboot.service.dataaccess.mongodb.ProductAccessEngine;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.PostgreSqlDbClient;
import com.ganga.www.sample.springboot.service.domain.service.search.ProductSearch;
import com.ganga.www.sample.springboot.service.domain.service.search.SearchFacade;
import com.ganga.www.sample.springboot.service.domain.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {

  @Bean
  SearchController searchController(
    final Validator<String> searchStringValidator,
    final Validator<String> productIdValidator,
    final SearchService searchService,
    final ResponseGenerator responseGenerator) {

    return new SearchController(searchStringValidator, productIdValidator,
      searchService, responseGenerator);
  }

  @Bean
  SearchService productSearchService(SearchFacade searchFacade,
                                     SearchEngine searchEngine) {
    return new ProductSearch(searchFacade, searchEngine);
  }

  @Bean
  SearchFacade searchFacade(ProductEngine productEngine,
                            CacheEngine<ProductMetadata> productSummaryCacheEngine,
                            CacheEngine<Product> productCacheEngine) {
    return new SearchFacade(productEngine, productSummaryCacheEngine,
      productCacheEngine);
  }

  @Bean
  ProductAccessEngine productAccessEngine(MongoDbClient mongoDbClient,
                                          PostgreSqlDbClient postgreSqlDbClient) {
    return new ProductAccessEngine(mongoDbClient, postgreSqlDbClient);
  }
}
