package com.productserver.service;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    @Order(1)
    public void registerProduct(){
        Member writer  = new Member();
        writer.setUserType(Member.UserType.WRITER);
        writer.setUserId("writer2");
        writer.setUserPassword("writer2");

        memberService.registerMember(writer);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductOwner(writer);
        productInfo.setPrice(150.0);

        Product product = new Product();
        product.setContents("컨텐츠");
        product.setTitle("타이틀");
        product.setLanguage(Product.Language.KOR);
        product.setProductInfo(productInfo);

        productInfo.getProductList().add(product);
        productInfoService.registerProductInfo(productInfo);
    }

    @Test
    @Order(2)
    public void getProductTest1(){
        ProductResponseDTO ret = productService.getProduct(2l);
        System.out.println(ret.getTitle() + " " + ret.getWriterId() + " " + ret.getPrice());
    }

}
