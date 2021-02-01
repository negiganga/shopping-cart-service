package com.ganga.www.sample.springboot.service.domain.service.order;

import com.ganga.www.sample.springboot.service.api.model.GetOrderResponse;
import com.ganga.www.sample.springboot.service.common.model.Order;
import com.ganga.www.sample.springboot.service.dataaccess.OrderEngine;
import com.ganga.www.sample.springboot.service.domain.service.OrderService;

public class OrderManagementService implements OrderService {

  private final OrderEngine orderEngine;

  public OrderManagementService(final OrderEngine orderEngine) {
    this.orderEngine = orderEngine;
  }

  /**
   * Ignored boundary cases and validation.
   */
  @Override
  public GetOrderResponse getOrder(String orderId) {
    GetOrderResponse response = new GetOrderResponse();
    response.setOrder(orderEngine.getOrder(orderId));
    return response;
  }

  /**
   * Ignored boundary cases and validation.
   */
  @Override
  public GetOrderResponse placeOrder(String userId, String shoppingCartId) {
    GetOrderResponse response = new GetOrderResponse();
    /*
    * Orchestrator:
    * 1. Using distributed transactional lock here, 3 phase commit:
    * 2. First, we may notify each respective service that we are gonna make an order.
    * 3. Second, We are making parallel calls to each services and waiting for their response.
    * 4. When all the services have confirmed their inventory availability, payment holding etc.
    * 5. Then, make a booking by taking a lock so that DB state remain consistent.
    * */
    // Prepare an order which will be carrying all the details for final booking.
    // Skipping due to time constraint.
    response.setOrder(orderEngine.confirmOrder(null));
    return response;
  }
}
