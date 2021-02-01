package com.ganga.www.sample.springboot.service.dataaccess;

import com.ganga.www.sample.springboot.service.common.model.Order;

public interface OrderEngine {
  Order getOrder(String orderId);

  Order confirmOrder(Order order);
}
