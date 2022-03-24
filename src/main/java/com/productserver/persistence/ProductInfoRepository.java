package com.productserver.persistence;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {
    List<ProductInfo> findAllByProductStatus(ProductInfo.Status status);
    List<ProductInfo> findAllByProductOwner(Member member);
    List<ProductInfo> findAllByEditor(Member member);
    @Query(" select a.productId as productInfoId, b.seq as productId, a.productOwner.userId as writerId, a.createDate as createDate, b.title as title, a.price as price, a.fee as fee, b.language as language, a.productStatus as status " +
            "from ProductInfo a, Product b " +
            "where a.productId = b.productInfo.productId and a.productStatus = ?1 and b.language = ?2")
    Iterable<ProductResponseDTO> findProductListWithStatusAndLanguage(ProductInfo.Status status, Product.Language language);

    @Transactional
    @Modifying
    @Query("update ProductInfo p set p.productStatus = ?1, p.updateDate = current_date where p.productId = ?2")
    int updateStatus(ProductInfo.Status productStatus,  Long productId);

    @Transactional
    @Modifying
    @Query("update ProductInfo p set p.fee = ?1, p.updateDate = current_date where p.productId = ?2")
    int updateFee(Double fee,  Long productId);

    @Transactional
    @Modifying
    @Query("update ProductInfo p set p.editor = ?1, p.updateDate = current_date where p.productId = ?2")
    int updateEditor(Member editor, Long productId);



}