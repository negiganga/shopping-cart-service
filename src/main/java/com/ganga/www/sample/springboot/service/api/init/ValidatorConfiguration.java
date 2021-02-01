package com.ganga.www.sample.springboot.service.api.init;

import com.ganga.www.sample.springboot.service.api.validator.ProductIdValidator;
import com.ganga.www.sample.springboot.service.api.validator.SearchStringValidator;
import com.ganga.www.sample.springboot.service.api.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfiguration {

  @Bean
  Validator<String> searchStringValidator() {
    return new SearchStringValidator();
  }

  @Bean
  Validator<String> productIdValidator() {
    return new ProductIdValidator();
  }
}
