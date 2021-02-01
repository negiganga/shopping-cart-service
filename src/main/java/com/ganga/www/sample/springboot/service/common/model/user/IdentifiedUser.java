package com.ganga.www.sample.springboot.service.common.model.user;

import lombok.Data;

@Data
public class IdentifiedUser extends User {
  private Account account;
}
