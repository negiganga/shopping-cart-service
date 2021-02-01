package com.ganga.www.sample.springboot.service.common.model.payment;

import com.ganga.www.sample.springboot.service.common.constants.PaymentType;
import lombok.Data;

@Data
public class PointsCard extends Card {
  PaymentType paymentType;

  public PointsCard() {
    paymentType = PaymentType.POINTS;
  }
}
