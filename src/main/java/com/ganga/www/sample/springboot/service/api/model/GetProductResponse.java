package com.ganga.www.sample.springboot.service.api.model;

import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import lombok.Data;

@Data
public class GetProductResponse extends BaseResponse {
  private Product product;
  private ProductReview productReview;
}
