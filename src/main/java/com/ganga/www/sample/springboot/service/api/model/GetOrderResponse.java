package com.ganga.www.sample.springboot.service.api.model;

import com.ganga.www.sample.springboot.service.common.model.Order;
import lombok.Data;

@Data
public class GetOrderResponse extends BaseResponse {
  // Should be different for checkout confirmation/order response.
  private Order order;
}
