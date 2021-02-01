package com.ganga.www.sample.springboot.service.api.validator;

import com.ganga.www.sample.springboot.service.api.exception.SearchProductException;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class SearchStringValidator implements Validator<String> {
  private static final Pattern STRING_MATCH_PATTERN =
    Pattern.compile("^[a-zA-Z0-9\\s]*$");

  @Override
  public void validate(String searchString) {
    if (!StringUtils.isEmpty(searchString)
      && !STRING_MATCH_PATTERN.matcher(searchString).find()) {
      throw new SearchProductException(SystemEvent.NO_MATCHING_PRODUCT,
        new StringBuilder(searchString).append(" not valid string.").toString());
    }
  }
}
