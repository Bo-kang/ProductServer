package com.productserver.persistence;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByLanguageAndProductInfo(Product.Language language, ProductInfo productInfo);

    @Transactional
    @Modifying
    @Query("update Product p set p.title = ?1, p.contents = ?2, p.language = ?3 where p.productInfo.productId = ?4 and p.seq = ?5")
    int updateTitleAndContentsAndLanguage(String title, String contents,Product.Language language ,Long productId, Long seq);

    @Query("select a.productId as productInfoId, b.seq as productId, a.productOwner.userId as writerId, a.createDate as createDate, b.title as title, a.price as price, a.fee as fee, b.language as language , b.contents as contents, a.productStatus as status " +
            "from ProductInfo a , Product b  " +
            "where b.seq = ?1 and b.productInfo.productId = a.productId")
    Optional<ProductResponseDTO> findProductDtoByProductSeq(Long seq);



}