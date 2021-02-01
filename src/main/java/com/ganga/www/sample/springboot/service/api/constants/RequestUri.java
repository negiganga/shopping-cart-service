package com.ganga.www.sample.springboot.service.api.constants;

public final class RequestUri {
    public static final String SHOPPING_REQ_URI = "/shoppingservice/api";
    public static final String BOOKING_REQ_URI = "/bookingservice/api";

    public static final String SEARCH_PRODUCTS_REQ_URI = "/v1/products/metadata";
    public static final String SEARCH_PRODUCT_REQ_URI = "/v1/products/{productId}";

    public static final String MAKE_ORDER_REQ_URI = "/v1/order";
    public static final String VIEW_ORDER_REQ_URI = "/v1/order/{orderId}";

    public static final String SHOPPING_CART_REQ_URI = "/v1/shoppingcart/{shoppingCartId}";
    public static final String ADD_ITEM_SHOPPING_CART_REQ_URI = "/v1/shoppingcart/item";
    public static final String REMOVE_ITEM_REQ_URI = "/v1/shoppingcart/item/{itemId}";
    public static final String INC_ITEM_COUNT_REQ_URI = "/v1/shoppingcart/item/{itemId}/plus";
    public static final String DEC_ITEM_COUNT_REQ_URI = "/v1/shoppingcart/item/{itemId}/minus";

    public static final String TEST_REQ_URI = "/test";
}
