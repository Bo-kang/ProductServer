package com.productserver.service;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;

import java.util.List;

public interface ProductInfoService {
    ProductInfo getProductInfo(Long productInfoId);

    ProductInfo registerProductInfo(ProductInfo productInfo);

    List<ProductResponseDTO> getProductList(ProductInfo.Status status, Product.Language language);

    void updateProductInfo(ProductInfo productInfo);

    List<ProductInfo> getProductListByWriter(Member member);

    List<ProductInfo> getProductListByEditor(Member member);
}
