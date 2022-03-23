package com.productserver.service;

import com.productserver.domain.Product;
import com.productserver.domain.ProductResponseDTO;
import com.productserver.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;

    @Override
    public Product registerProduct(Product product){
        Product ret = productRepo.save(product);
        return ret;
    }

    @Override
    public ProductResponseDTO getProduct(Long productId) {
        return productRepo.findProductDtoByProductSeq(productId).orElse(null);
    }


}
