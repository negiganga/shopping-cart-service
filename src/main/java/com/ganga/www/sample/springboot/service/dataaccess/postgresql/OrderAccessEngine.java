package com.ganga.www.sample.springboot.service.dataaccess.postgresql;

import com.ganga.www.sample.springboot.service.common.model.Order;
import com.ganga.www.sample.springboot.service.dataaccess.OrderEngine;

public class OrderAccessEngine implements OrderEngine {

  private final PostgreSqlDbClient postgreSqlDbClient;

  public OrderAccessEngine(final PostgreSqlDbClient postgreSqlDbClient) {
    this.postgreSqlDbClient = postgreSqlDbClient;
  }

  @Override
  public Order getOrder(String orderId) {
    return postgreSqlDbClient.getOrder(orderId);
  }

  @Override
  public Order confirmOrder(Order order) {
    // Could not cover due to time constraint.
    return null;
  }
}
