package com.productserver.persistence;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByLanguageAndProductInfo(Product.Language language, ProductInfo productInfo);

    @Transactional
    @Modifying
    @Query("update Product p set p.title = ?1, p.contents = ?2, p.language = ?3 where p.productInfo.productId = ?4 and p.seq = ?5")
    int updateTitleAndContentsAndLanguage(String title, String contents,Product.Language language ,Long productId, Long seq);



}