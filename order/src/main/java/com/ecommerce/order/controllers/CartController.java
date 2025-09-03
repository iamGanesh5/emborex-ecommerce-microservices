package com.ecommerce.order.controllers;


import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.models.CartAddStatus;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request) {
        if (!cartService.addToCart(userId, request)) {
            return ResponseEntity.badRequest().body("Product out of stock");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to the cart");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable Long productId) {

        boolean deleted = cartService.deleteItemFromCart(userId, String.valueOf(productId));
        return deleted
                ? ResponseEntity.ok("Item deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

//    @PostMapping
//    public ResponseEntity<String> addToCart(
//            @RequestHeader("X-User-ID") String userId,
//            @RequestBody CartItemRequest request) {
//
//        CartAddStatus status = cartService.addToCart(userId, request);
//
//        switch (status) {
//            case OUT_OF_STOCK:
//                return ResponseEntity.badRequest().body("Product is out of stock");
//            case QUANTITY_UPDATED:
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body("Product already in cart. Quantity updated.");
//            case ADDED:
//                return ResponseEntity.status(HttpStatus.CREATED)
//                        .body("Product added to the cart");
//            default:
//                return ResponseEntity.internalServerError().body("Unexpected error");
//        }
//
   }

