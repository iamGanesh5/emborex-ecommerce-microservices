package com.ecommerce.order.clients;

import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.user.dto.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserServiceClient {
    @GetExchange("/api/user/{id}")
    UserResponse uSERTDetails(@PathVariable String id);
}
