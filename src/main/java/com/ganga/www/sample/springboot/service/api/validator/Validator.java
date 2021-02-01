package com.ganga.www.sample.springboot.service.api.validator;

public interface Validator<T> {

  void validate(T object);

}
