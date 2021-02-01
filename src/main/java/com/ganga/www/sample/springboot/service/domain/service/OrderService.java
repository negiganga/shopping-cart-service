package com.ganga.www.sample.springboot.service.domain.service;

import com.ganga.www.sample.springboot.service.api.model.GetOrderResponse;

public interface OrderService {
  GetOrderResponse getOrder(String orderId);

  GetOrderResponse placeOrder(String userId, String shoppingCartId);
}
