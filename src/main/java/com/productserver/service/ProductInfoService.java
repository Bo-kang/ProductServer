package com.productserver.service;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;

import java.util.List;

public interface ProductInfoService {
    ProductInfo getProductInfo(ProductInfo productInfo);

    ProductInfo registerProductInfo(ProductInfo productInfo);

    List<ProductResponseDTO> getProductList(ProductInfo.Status status, Product.Language language);

    void updateProductInfo(ProductInfo productInfo);
}
