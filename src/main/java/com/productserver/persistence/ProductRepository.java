package com.productserver.persistence;

import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByLanguageAndProductInfo(Product.Language language, ProductInfo productInfo);
}