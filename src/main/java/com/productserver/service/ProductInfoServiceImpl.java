package com.productserver.service;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductListResponseDTO;
import com.productserver.persistence.ProductInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoRepository productInfoRepo;

    @Override
    public ProductInfo getProductInfo(ProductInfo productInfo){
        Optional<ProductInfo> ret = productInfoRepo.findById(productInfo.getProductId());

        return ret.orElse(null);
    }

    @Override
    public ProductInfo registerProductInfo(ProductInfo productInfo){

        return productInfoRepo.save(productInfo);
    }

    @Override
    public List<ProductListResponseDTO> getProductList(ProductInfo.Status status, Product.Language language){
        return (List<ProductListResponseDTO>)productInfoRepo.findProductListWithStatusAndLanguage(status, language);
    }

}
