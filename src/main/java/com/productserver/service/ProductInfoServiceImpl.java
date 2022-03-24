package com.productserver.service;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;
import com.productserver.persistence.ProductInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoRepository productInfoRepo;

    @Override
    public ProductInfo getProductInfo(Long productInfoId){
        Optional<ProductInfo> ret = productInfoRepo.findById(productInfoId);

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
        if(productInfo.getEditor() != null)
            productInfoRepo.updateEditor(productInfo.getEditor(), productInfo.getProductId());
        if( productInfo.getFee() != null && !productInfo.getFee().isNaN())
            productInfoRepo.updateFee(productInfo.getFee(), productInfo.getProductId());
    }

    @Override
    @Transactional
    public List<ProductInfo> getProductListByWriter(Member member) {
        return productInfoRepo.findAllByProductOwner(member);
    }

    @Override
    public List<ProductInfo> getProductListByEditor(Member member) {
        return productInfoRepo.findAllByEditor(member);
    }

}
