package com.productserver.persistence;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductListResponseDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {
    List<ProductInfo> findAllByProductStatus(ProductInfo.Status status);




}