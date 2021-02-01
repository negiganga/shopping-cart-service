package com.ganga.www.sample.springboot.service.api.controller;

import com.ganga.www.sample.springboot.service.api.constants.RequestUri;
import com.ganga.www.sample.springboot.service.api.exception.ShoppingCartException;
import com.ganga.www.sample.springboot.service.api.model.BaseResponse;
import com.ganga.www.sample.springboot.service.api.model.GetShoppingCartResponse;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.common.constants.ColorType;
import com.ganga.www.sample.springboot.service.common.constants.SizeType;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.domain.service.IShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(RequestUri.SHOPPING_REQ_URI)
public class ShoppingCartController {

  private final IShoppingCartService shoppingCartService;
  private final ResponseGenerator responseGenerator;

  public ShoppingCartController(final IShoppingCartService shoppingCartService,
                                final ResponseGenerator responseGenerator) {
    this.shoppingCartService = shoppingCartService;
    this.responseGenerator = responseGenerator;
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.SHOPPING_CART_REQ_URI, method = RequestMethod.GET)
  public ResponseEntity viewShoppingCart(
    @PathVariable("shoppingCartId") String shoppingCartId) {
    // 1. Validate Input
    // 2. process shopping cart
    final GetShoppingCartResponse response;
    try{
      response = shoppingCartService.getShoppingCart(shoppingCartId);
    } catch (ShoppingCartException e) {
      log.error("Shopping cart {} cannot be view because of an exception {}",
        shoppingCartId, e);
      return responseGenerator.error(
        new StringBuilder("Shopping cart ").append(shoppingCartId)
          .append(" encountered an exception.").append(". ")
          .append(e.getMessage()).toString(),
        SystemEvent.SHOPPING_CART_VIEW_EXCEPTION, HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.OK, response);
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.ADD_ITEM_SHOPPING_CART_REQ_URI, method = RequestMethod.POST)
  public ResponseEntity addItemShoppingCart(
    @RequestParam(value = "productId") String productId,
    @RequestParam(value = "shoppingCartId") String shoppingCartId,
    @RequestParam(value = "quantity", defaultValue = "1") int quantity,
    @RequestParam(value = "size") SizeType size,
    @RequestParam(value = "color", required = false) ColorType color) {

    // 1. Validate Input
    // 2. process shopping cart
    try{
      shoppingCartService.addItemShoppingCart(
        shoppingCartId, productId, quantity, size, color
      );
    } catch (ShoppingCartException e) {
      log.error("Add item {} to shopping cart {} because of an exception {}",
        productId, shoppingCartId, e);
      return responseGenerator.error(
        new StringBuilder("Product ").append(productId)
          .append(" failed from getting added to the shopping cart ")
          .append(shoppingCartId).append(". ").append(e.getMessage()).toString(),
        SystemEvent.ADD_ITEM_TO_SHOPPING_CART_EXCEPTION, HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.CREATED, new BaseResponse());
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.REMOVE_ITEM_REQ_URI, method = RequestMethod.DELETE)
  public ResponseEntity removeItemShoppingCart(
    @PathVariable("itemId") String itemId,
    @RequestParam(value = "shoppingCartId") String shoppingCartId) {
    // 1. Validate Input
    // 2. process shopping cart
    try{
      shoppingCartService.removeItemShoppingCart(shoppingCartId, itemId);
    } catch (ShoppingCartException e) {
      log.error("Item {} cannot be removed from shopping cart {} " +
          "because of an exception {}", itemId, shoppingCartId, e);
      return responseGenerator.error(
        new StringBuilder("Item ").append(itemId)
          .append(" cannot be removed from shopping cart ")
          .append(shoppingCartId)
          .append(". ").append(e.getMessage()).toString(),
        SystemEvent.SHOPPING_CART_VIEW_EXCEPTION, HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.NO_CONTENT, new BaseResponse());
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.INC_ITEM_COUNT_REQ_URI, method = RequestMethod.PUT)
  public ResponseEntity<String> incrementItemCountShoppingCart(
    @PathVariable("itemId") String itemId,
    @RequestParam(value = "count", defaultValue = "1") int count) {

    // 1. Validate Input
    // 2. process shopping cart

    // TODO:
    // Catch Exception if this operation is not feasible because of less
    // availability of the items in inventory. Raise an exception with appropriate message.

    try{
      shoppingCartService.incrementItemQuantityShoppingCart(itemId, count);
    } catch (ShoppingCartException e) {
      log.error("Item {} cannot be incremented in shopping cart " +
        "because of an exception {}", itemId, e);
      return responseGenerator.error(
        new StringBuilder("Item ").append(itemId)
          .append(" cannot be incremented in shopping cart. ")
          .append(e.getMessage()).toString(),
        SystemEvent.INC_ITEM_IN_SHOPPING_CART_EXCEPTION, HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.OK, new BaseResponse());
  }

  @ResponseBody
  @RequestMapping(value = RequestUri.DEC_ITEM_COUNT_REQ_URI, method = RequestMethod.PUT)
  public ResponseEntity<String> decrementItemCountShoppingCart(
    @PathVariable("itemId") String itemId,
    @RequestParam(value = "count", defaultValue = "1") int count) {
    // 1. Validate Input
    // 2. process shopping cart

    // TODO:
    // Raise exception if item is no longer available even for the valid count of item.
    try{
      shoppingCartService.decrementItemQuantityShoppingCart(itemId, count);
    } catch (ShoppingCartException e) {
      log.error("Item {} cannot be decremented in shopping cart " +
        "because of an exception {}", itemId, e);
      return responseGenerator.error(
        new StringBuilder("Item ").append(itemId)
          .append(" cannot be decremented in shopping cart. ")
          .append(e.getMessage()).toString(),
        SystemEvent.DEC_ITEM_IN_SHOPPING_CART_EXCEPTION, HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.OK, new BaseResponse());
  }
}
