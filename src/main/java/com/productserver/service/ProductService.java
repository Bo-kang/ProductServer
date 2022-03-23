package com.productserver.service;

import com.productserver.domain.Product;
import com.productserver.domain.ProductResponseDTO;

public interface ProductService {
    Product registerProduct(Product product);
    ProductResponseDTO getProduct(Long productId);
}
