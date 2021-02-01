package com.ganga.www.sample.springboot.service.api.controller;

import com.ganga.www.sample.springboot.service.api.constants.RequestUri;
import com.ganga.www.sample.springboot.service.api.model.GetOrderResponse;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestUri.BOOKING_REQ_URI)
public class OrderController {

  private final OrderService orderService;
  private final ResponseGenerator responseGenerator;

  public OrderController(final OrderService orderService,
                         final ResponseGenerator responseGenerator) {
    this.orderService = orderService;
    this.responseGenerator = responseGenerator;
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.MAKE_ORDER_REQ_URI, method = RequestMethod.POST)
  public ResponseEntity placeOrder(
    @RequestHeader("user-id") String userId,
    @RequestParam("shoppingCartId") String shoppingCartId
  ) {
    // 1. validate input
    final GetOrderResponse response = orderService.placeOrder(userId, shoppingCartId);
    return responseGenerator.success(HttpStatus.CREATED, response);
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.VIEW_ORDER_REQ_URI, method = RequestMethod.GET)
  public ResponseEntity viewOrder(
    @PathVariable("orderId") String orderId) {

    // 1. validate input
    final GetOrderResponse response = orderService.getOrder(orderId);
    return responseGenerator.success(HttpStatus.OK, response);
  }
}
