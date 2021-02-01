package com.ganga.www.sample.springboot.service.common.model;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {
  private String id;
  private List<Item> items;
  private double total;
}
