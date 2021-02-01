package com.ganga.www.sample.springboot.service.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

  protected String statusDescription;
  protected int statusId;
  protected String statusName;
  protected StatusType statusType;

  enum StatusType {
    SUCCESS,
    WARNING,
    FAILED;
  }
}
