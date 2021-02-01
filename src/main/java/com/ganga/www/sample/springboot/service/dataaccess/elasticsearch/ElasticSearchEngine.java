package com.ganga.www.sample.springboot.service.dataaccess.elasticsearch;

import com.ganga.www.sample.springboot.service.dataaccess.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElasticSearchEngine implements SearchEngine {

  private static final Map<String, String> SAMPLE_DATA =
      Map.of(
          "Name 1", "1234",
          "Check 2", "2345",
          "Test 3", "3456",
          "Sample 4", "4567",
          "Name 5", "5678");

  private ElasticSearchClient elasticSearchClient;

  public ElasticSearchEngine(final ElasticSearchClient elasticSearchClient) {
    this.elasticSearchClient = elasticSearchClient;
  }

  /**
   * This method is MOCKED with sample response for now.
   *
   * Though here we are supposed to perform operations on the top of results
   * received from elastic search client i.e. elasticSearchClient.
   */
  @Override
  public List<String> getProductIds(final String name) {
    final List<String> matchedProductIds = new ArrayList<>();
    for (Map.Entry<String, String> entry : SAMPLE_DATA.entrySet()) {
      if (entry.getKey().contains(name)) {
        matchedProductIds.add(entry.getValue());
      }
    }
    return matchedProductIds;
  }
}
