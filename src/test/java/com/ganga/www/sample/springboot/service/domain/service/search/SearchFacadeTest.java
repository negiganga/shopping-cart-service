package com.ganga.www.sample.springboot.service.domain.service.search;

import com.ganga.www.sample.springboot.service.api.exception.SearchProductException;
import com.ganga.www.sample.springboot.service.common.model.product.Product;
import com.ganga.www.sample.springboot.service.common.model.product.ProductMetadata;
import com.ganga.www.sample.springboot.service.common.model.product.ProductReview;
import com.ganga.www.sample.springboot.service.common.monitoring.SystemEvent;
import com.ganga.www.sample.springboot.service.dataaccess.CacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.ProductEngine;
import com.ganga.www.sample.springboot.service.dataaccess.cache.redis.ProductCacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.cache.redis.ProductMetadataCacheEngine;
import com.ganga.www.sample.springboot.service.dataaccess.exception.ProductAccessDbException;
import com.ganga.www.sample.springboot.service.dataaccess.exception.RedisAccessException;
import com.ganga.www.sample.springboot.service.dataaccess.mongodb.ProductAccessEngine;
import com.ganga.www.sample.springboot.service.domain.mock.ProductMetadataMock;
import com.ganga.www.sample.springboot.service.domain.mock.ProductMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SearchFacadeTest {

  private ProductEngine productEngineMock;
  private CacheEngine<ProductMetadata> productMetadataCacheEngineMock;
  private CacheEngine<Product> productCacheEngineMock;

  private SearchFacade searchFacade;

  @Before
  public void setUp() {
    productEngineMock = Mockito.mock(ProductAccessEngine.class);
    productMetadataCacheEngineMock = Mockito.mock(ProductMetadataCacheEngine.class);
    productCacheEngineMock = Mockito.mock(ProductCacheEngine.class);

    searchFacade = new SearchFacade(productEngineMock,
      productMetadataCacheEngineMock, productCacheEngineMock);
  }

  @Test
  public void testGetCachedProductsMetadata() throws RedisAccessException {
    final ProductMetadata productMetadata = ProductMetadataMock.init();
    Mockito.when(
            productMetadataCacheEngineMock.getProducts(
                Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
        .thenReturn(Arrays.asList(productMetadata));
    final List<ProductMetadata> cachedProductsMetadata
      = searchFacade.getCachedProductsMetadata("Product", 10, "nextPage");

    assertNotNull(cachedProductsMetadata);
    assertEquals("productID111", cachedProductsMetadata.get(0).getProductId());
  }

  @Test(expected = RedisAccessException.class)
  public void testGetCachedProductsMetadataThrowException() throws RedisAccessException {
    Mockito.when(
      productMetadataCacheEngineMock.getProducts(
        Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
      .thenThrow(new RedisAccessException(SystemEvent.INVALID_REQUEST));
    searchFacade.getCachedProductsMetadata("Orange", 10, "nextPage");
  }

  @Test
  public void testGetProductReview() {
    final ProductReview productReviewMock = Mockito.mock(ProductReview.class);
    Mockito.when(productEngineMock.fetchProductReview(Mockito.anyString()))
      .thenReturn(productReviewMock);
    final ProductReview productReview =
      searchFacade.getProductReview("productID111");

    assertNotNull(productReview);
  }

  @Test
  public void testGetProductMetadata() throws RedisAccessException {
    final ProductMetadata productMetadataMock = ProductMetadataMock.init();
    Mockito.when(
      productEngineMock.fetchTopSearchProductsMetadata(Mockito.anyList()))
      .thenReturn(Arrays.asList(productMetadataMock));
    final List<ProductMetadata> productMetadataList =
      searchFacade.getProductMetadataList(Arrays.asList("productID111"));

    assertNotNull(productMetadataList);
    assertEquals("productID111", productMetadataList.get(0).getProductId());
  }

  @Test
  public void testGetProduct() throws RedisAccessException {
    final Product productMock = ProductMock.init();
    Mockito.when(
      productCacheEngineMock.getProduct(Mockito.anyString()))
      .thenReturn(productMock);
    final Product product = searchFacade.getProduct("productID111");

    assertNotNull(product);
    assertEquals("productID30", product.getProductId());
  }

  @Test(expected = SearchProductException.class)
  public void testGetProductThrowException()
    throws RedisAccessException, ProductAccessDbException {
    Mockito.when(
      productCacheEngineMock.getProduct(Mockito.anyString()))
      .thenThrow(new RedisAccessException(SystemEvent.INVALID_REQUEST));
    Mockito.when(
      productEngineMock.fetchProduct(Mockito.anyString()))
      .thenThrow(new ProductAccessDbException(SystemEvent.INVALID_REQUEST));
    searchFacade.getProduct("productID111");
  }
}