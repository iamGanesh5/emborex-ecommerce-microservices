package com.ecommerce.order.services;


import com.ecommerce.order.clients.ProductServicesClientConfig;
import com.ecommerce.order.clients.UserServiceClient;
import com.ecommerce.order.clients.UserServicesClientConfig;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.models.CartAddStatus;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import com.ecommerce.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.uid;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductServicesClientConfig productServicesClientConfig;
    private final UserServiceClient userServiceClient;
//    private  final UserRepository userRepository;
//    private final ProductRepository productRepository;

    @Override
    public boolean addToCart(String userId, CartItemRequest request) {
        // 1. Fetch product details and check stock
        ProductResponse product = productServicesClientConfig.getProductDetails(request.getProductId());
        if (product == null || product.getStockQuantity() < request.getQuantity()) {
            return false; // Product not found or not enough stock
        }

        // 2. Fetch user details
        UserResponse userResponse = userServiceClient.uSERTDetails(userId);
        if (userResponse == null) {
            return false; // User not found
        }

        // 3. Convert userId and productId to Long
        Long uid = Long.valueOf(userId);
        Long pid = Long.valueOf(request.getProductId());

        // 4. Check if the cart item already exists
        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(uid, pid);

        if (existingCartItem != null) {
            // Update quantity and price if item already in cart
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00)); // Replace with actual logic if needed
            cartItemRepository.save(existingCartItem);
        } else {
            // Add new item to cart
            CartItem cartItem = new CartItem();
            cartItem.setUserId(uid);
            cartItem.setProductId(pid);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00)); // Replace with actual logic if needed
            cartItemRepository.save(cartItem);
        }

        return true;
    }

//@Override
//public CartAddStatus addToCart(String userId, CartItemRequest request) {
//    ProductResponse product = productServicesClientConfig.getProductDetails(request.getProductId());
//
//    if (product == null || product.getStockQuantity() < request.getQuantity()) {
//        return CartAddStatus.OUT_OF_STOCK;
//    }
//// 3. Look for user
//    UserResponse userresponse= userServiceClient.uSERTDetails(userId);
//            if(userresponse == null ){
//                return CartAddStatus.USER_NOT_FOUND;
//       }
//    Long uid = Long.valueOf(userId);
//    Long pid = Long.valueOf(request.getProductId());
//
//    CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(uid, pid);
//
//    if (existingCartItem != null) {
//        existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
//        existingCartItem.setPrice(BigDecimal.valueOf(1000.00)); // Placeholder
//        cartItemRepository.save(existingCartItem);
//        return CartAddStatus.QUANTITY_UPDATED;
//    } else {
//        CartItem cartItem = new CartItem();
//        cartItem.setUserId(uid);
//        cartItem.setProductId(pid);
//        cartItem.setQuantity(request.getQuantity());
//        cartItem.setPrice(BigDecimal.valueOf(1000.00)); // Placeholder
//        cartItemRepository.save(cartItem);
//        return CartAddStatus.ADDED;
//    }
//}
//

    @Override
    public boolean deleteItemFromCart(String userId, String productId) {
        try {
            // ✅ Convert both IDs to Long
            Long uid = Long.valueOf(userId);
            Long pid = Long.valueOf(productId);

            // ✅ Now call repository method with Longs
            CartItem cartItem = cartItemRepository.findByUserIdAndProductId(uid, pid);
            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
                return true;
            }
        } catch (NumberFormatException e) {
            // Log the error or return false silently
            System.err.println("Invalid ID format: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(String userId) {
//        Long id = Long.parseLong(userId);
//        return cartItemRepository.findByUserId(id);
        return cartItemRepository.findByUserId(Long.valueOf(userId));
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
//        userRepository.findById(Long.valueOf(userId))
//                .ifPresent(cartItemRepository::deleteByUser);
         cartItemRepository.deleteByUserId(Long.valueOf(userId));
    }


}
