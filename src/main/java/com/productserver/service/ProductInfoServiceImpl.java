package com.productserver.service;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;
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
    public List<ProductResponseDTO> getProductList(ProductInfo.Status status, Product.Language language){
        return (List<ProductResponseDTO>)productInfoRepo.findProductListWithStatusAndLanguage(status, language);
    }

    @Override
    public void updateProductInfo(ProductInfo productInfo) {
        if(productInfo.getProductStatus() != null)
            productInfoRepo.updateStatus(productInfo.getProductStatus(), productInfo.getProductId());
        if(productInfo.getProductOwner() != null)
            productInfoRepo.updateEditor(productInfo.getEditorId(), productInfo.getProductId());
        if(!productInfo.getFee().isNaN() && productInfo.getFee() != null)
            productInfoRepo.updateFee(productInfo.getFee(), productInfo.getProductId());
    }

}