package com.ganga.www.sample.springboot.service.common.model.product;

import com.ganga.www.sample.springboot.service.common.constants.CurrencyCode;
import lombok.Data;

@Data
public class Price {
  private CurrencyCode currencyCode;
  private double amount;

  public Price(double price,
               CurrencyCode currencyCode) {
    this.amount = price;
    this.currencyCode = currencyCode;
  }
}
