package com.ganga.www.sample.springboot.service.common.model.payment;

import com.ganga.www.sample.springboot.service.common.constants.CardProviderType;
import lombok.Data;

@Data
public abstract class Card {
  protected String cardNumber;
  protected String cardHolderName;
  protected Integer cvvNumber;
  protected Integer monthOfExpiry;
  protected Integer yearOfExpiry;
  protected CardProviderType providerType;
}
