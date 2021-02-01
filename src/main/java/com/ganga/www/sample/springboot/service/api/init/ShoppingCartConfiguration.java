package com.ganga.www.sample.springboot.service.api.init;

import com.ganga.www.sample.springboot.service.api.controller.ShoppingCartController;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.PostgreSqlDbClient;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.ShoppingCartAccessEngine;
import com.ganga.www.sample.springboot.service.domain.service.IShoppingCartService;
import com.ganga.www.sample.springboot.service.domain.service.search.SearchFacade;
import com.ganga.www.sample.springboot.service.domain.service.shoppingcart.ShoppingCartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartConfiguration {

  @Bean
  ShoppingCartController shoppingCartController(IShoppingCartService shoppingCartService,
                                                ResponseGenerator responseGenerator) {
    return new ShoppingCartController(shoppingCartService, responseGenerator);
  }

  @Bean
  IShoppingCartService shoppingCartService(ShoppingCartAccessEngine shoppingCartAccessEngine,
                                           SearchFacade searchFacade) {
    return new ShoppingCartService(shoppingCartAccessEngine,
      searchFacade);
  }

  @Bean
  ShoppingCartAccessEngine shoppingCartAccessEngine(PostgreSqlDbClient postgreSqlDbClient) {
    return new ShoppingCartAccessEngine(postgreSqlDbClient);
  }
}
