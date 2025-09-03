package com.ecommerce.product.services;



import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    Optional<ProductResponse> getProductById(String id); // ✅ Fixed type

    Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest); // ✅ Fixed type

    boolean deleteProduct(Long id);

    List<ProductResponse> searchProducts(String keyword);
}
