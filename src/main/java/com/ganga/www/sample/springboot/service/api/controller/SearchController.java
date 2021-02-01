package com.ganga.www.sample.springboot.service.api.controller;

import com.ganga.www.sample.springboot.service.api.constants.RequestUri;
import com.ganga.www.sample.springboot.service.api.exception.SearchProductException;
import com.ganga.www.sample.springboot.service.api.model.GetProductResponse;
import com.ganga.www.sample.springboot.service.api.model.GetProductsMetadataResponse;
import com.ganga.www.sample.springboot.service.api.model.ResponseGenerator;
import com.ganga.www.sample.springboot.service.api.validator.Validator;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.domain.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(RequestUri.SHOPPING_REQ_URI)
public class SearchController {

    private Validator<String> searchStringValidator;
    private Validator<String> productIdValidator;
    private SearchService searchService;
    private ResponseGenerator responseGenerator;

  public SearchController(
      final Validator<String> searchStringValidator,
      final Validator<String> productIdValidator,
      final SearchService searchService,
      final ResponseGenerator responseGenerator) {
        this.searchStringValidator = searchStringValidator;
        this.productIdValidator = productIdValidator;
        this.searchService = searchService;
        this.responseGenerator = responseGenerator;
    }

  @ResponseBody
  @RequestMapping(
    value = RequestUri.SEARCH_PRODUCTS_REQ_URI,
    method = RequestMethod.GET)
  public ResponseEntity searchProductsSummary(
      @RequestParam(value = "name", defaultValue = "") String name,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "nextPageIndex", required = false) String nextPageIndex) {

      searchStringValidator.validate(name);
      final GetProductsMetadataResponse response;
      try{
        response = searchService.searchProducts(name, limit, nextPageIndex);
      } catch (SearchProductException e) {
        log.error("Products search controller encountered an exception" +
          " with search keyword {}. {}.", name, e);
        return responseGenerator.error(
          new StringBuilder("No products found with search keyword ").append(name).toString(),
          SystemEvent.SEARCH_PRODUCTS_EXCEPTION, HttpStatus.NOT_FOUND);
      }
      return responseGenerator.success(HttpStatus.OK, response);
  }

  @ResponseBody
  @RequestMapping(
    value = RequestUri.SEARCH_PRODUCT_REQ_URI,
    method = RequestMethod.GET)
  public ResponseEntity getProduct(@PathVariable String productId) {

    productIdValidator.validate(productId);
    final GetProductResponse response;
    try{
      response = searchService.getProduct(productId);
    } catch (SearchProductException e) {
      log.error("Product search controller encountered an exception" +
        " with product ID {}.  {}.", productId, e);
      return responseGenerator.error(
          new StringBuilder("Product search controller encountered an exception with product ID ")
              .append(productId)
              .toString(),
          SystemEvent.SEARCH_PRODUCT_EXCEPTION,
          HttpStatus.NOT_FOUND);
    }
    return responseGenerator.success(HttpStatus.OK, response);
  }
}
