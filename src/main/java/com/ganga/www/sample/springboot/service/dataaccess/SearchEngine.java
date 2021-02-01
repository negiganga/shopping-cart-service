package com.ganga.www.sample.springboot.service.dataaccess;

import java.util.List;

public interface SearchEngine {
  /**
   * This method is responsible for making a query to elastic search
   * engine and fetching all the possible matching product IDs with
   * the user entered search string.
   * @param name : User's entered string.
   *
   * @return List of matching product IDs.
   */
  List<String> getProductIds(String name);
}
