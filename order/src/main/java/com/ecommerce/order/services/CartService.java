package com.ecommerce.order.services;


import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.models.CartAddStatus;
import com.ecommerce.order.models.CartItem;

import java.util.List;

public interface CartService {

//    CartAddStatus addToCart(String userId, CartItemRequest request);
    boolean addToCart(String userId, CartItemRequest request);

    boolean deleteItemFromCart(String userId, String productId);
    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
