package com.ganga.www.sample.springboot.service.api.validator;

import com.ganga.www.sample.springboot.service.api.exception.SearchProductException;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class ProductIdValidator implements Validator<String> {
  /**
   * We can use the pattern which matches to our ID if any.
   * I am assuming below for now.
   */
  private static final Pattern STRING_MATCH_PATTERN =
    Pattern.compile("^[a-zA-Z0-9\\s]*$");

  @Override
  public void validate(final String productId) {
    if (StringUtils.isEmpty(productId)) {
      throw new SearchProductException(SystemEvent.INVALID_PRODUCT_ID,
        "Empty product ID found.");
    } else if (!STRING_MATCH_PATTERN.matcher(productId).find()) {
      throw new SearchProductException(SystemEvent.INVALID_PRODUCT_ID,
        new StringBuilder(productId).append(" not a valid product ID.").toString());
    }
  }
}
