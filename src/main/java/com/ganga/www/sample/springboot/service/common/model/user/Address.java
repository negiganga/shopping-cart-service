package com.ganga.www.sample.springboot.service.common.model.user;

import lombok.Data;

@Data
public class Address {
  private String unit;
  private String streetName;
  private String city;
  private String state;
  private String zipCode;
  private String country;
}
