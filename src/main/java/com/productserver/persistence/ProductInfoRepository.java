package com.productserver.persistence;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductListResponseDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {
    List<ProductInfo> findAllByProductStatus(ProductInfo.Status status);

    @Query(" select a.productId as productInfoId, b.seq as productId, a.productOwner.userId as writerId, a.createDate as createDate, b.title as title, a.price as price, a.fee as fee, b.language as language " +
            "from ProductInfo a, Product b " +
            "where a.productId = b.productInfo.productId and a.productStatus = ?1 and b.language = ?2")
    Iterable<ProductListResponseDTO> findProductListWithStatusAndLanguage(ProductInfo.Status status,Product.Language language);

    @Transactional
    @Modifying
    @Query("update ProductInfo p set p.productStatus = ?1, p.updateDate = current_time where p.productId = ?3")
    int updateStatus(ProductInfo.Status productStatus,  Long productId);

    @Transactional
    @Modifying
    @Query("update ProductInfo p set p.fee = ?1, p.updateDate = current_time where p.productId = ?3")
    int updateFee(Double fee,  Long productId);



}