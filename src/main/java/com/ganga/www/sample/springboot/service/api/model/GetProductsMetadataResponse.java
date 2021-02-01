package com.ganga.www.sample.springboot.service.api.model;

import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetProductsMetadataResponse extends BaseResponse {
  private ArrayList<ProductMetadata> productsMetadata;
}
