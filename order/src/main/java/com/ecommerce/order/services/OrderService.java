package com.ecommerce.order.services;

import java.util.Optional;

public interface OrderService {
    Optional<Object> createOrder(String userId);
}
