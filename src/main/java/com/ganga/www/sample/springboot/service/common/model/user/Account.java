package com.ganga.www.sample.springboot.service.common.model.user;

import com.ganga.www.sample.springboot.service.common.constants.AccountStatus;
import com.ganga.www.sample.springboot.service.common.model.payment.Card;
import lombok.Data;

import java.util.List;

@Data
public class Account {
  private String userName;
  private String password;
  private AccountStatus status;
  private String name;
  private Address shippingAddress;
  private String email;
  private String phoneNumber;

  private List<Card> paymentCards;
}
