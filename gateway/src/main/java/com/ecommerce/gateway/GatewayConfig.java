package com.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfig {

//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // Product Service
                .route("product-service", r -> r
                        .path("/products/**")
                        .filters(f -> f.rewritePath("/products(?<segment>/?.*)", "/api/products${segment}"))

                        .uri("lb://PRODUCT-SERVICE"))

                // User Service
//                .route("user-service", r -> r
//                        .path("/api/users/**")
//                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)", "/api/users${segment}"))
//                        .uri("lb://USER-SERVICE"))
                .route("user-service", r -> r
                        .path("/users/**")
                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)", "/api/users${segment}"))
                        .uri("lb://USER-SERVICE"))
                // Order Service
                .route("order-service", r -> r
                        .path("/orders/**")
                        .filters(f -> f.rewritePath("/orders(?<segment>/?.*)", "/api/orders${segment}"))

                        .uri("lb://ORDER-SERVICE"))

                // Cart Service
                .route("cart-service", r -> r
                        .path("/cart/**")
                        .filters(f -> f.rewritePath("/cart(?<segment>/?.*)", "/api/cart${segment}"))

                        .uri("lb://ORDER-SERVICE")) // cart lives in order-service

                .build();
    }
}
