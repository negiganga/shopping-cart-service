package com.ganga.www.sample.springboot.service.common.model.payment;

import com.ganga.www.sample.springboot.service.common.constants.PaymentType;
import lombok.Data;

@Data
public class DebitCard extends Card {
  PaymentType paymentType;

  public DebitCard() {
    paymentType = PaymentType.DEBIT;
  }
}
