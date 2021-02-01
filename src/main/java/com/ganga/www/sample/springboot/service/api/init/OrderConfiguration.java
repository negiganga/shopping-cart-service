package com.ganga.www.sample.springboot.service.api.init;

import com.ganga.www.sample.springboot.service.api.controller.OrderController;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.dataaccess.OrderEngine;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.OrderAccessEngine;
import com.ganga.www.sample.springboot.service.dataaccess.postgresql.PostgreSqlDbClient;
import com.ganga.www.sample.springboot.service.domain.service.OrderService;
import com.ganga.www.sample.springboot.service.domain.service.order.OrderManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

  @Bean
  public OrderController orderController(OrderService orderService,
                                         ResponseGenerator responseGenerator) {
    return new OrderController(orderService, responseGenerator);
  }

  @Bean
  public OrderService orderService(OrderEngine orderEngine) {
    return new OrderManagementService(orderEngine);
  }

  @Bean
  public OrderEngine orderEngine(PostgreSqlDbClient postgreSqlDbClient) {
    return new OrderAccessEngine(postgreSqlDbClient);
  }
}
