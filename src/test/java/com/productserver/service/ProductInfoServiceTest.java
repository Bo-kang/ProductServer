package com.productserver.service;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductInfoServiceTest {

    @Autowired
    private  MemberServiceImpl memberService;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    @Order(1)
    public void registerMember(){
        Member writer  = new Member();
        writer.setUserType(Member.UserType.WRITER);
        writer.setUserId("writer1");
        writer.setUserPassword("writer1");

        memberService.registerMember(writer);

        Member editor  = new Member();
        editor.setUserType(Member.UserType.EDITOR);
        editor.setUserId("editor1");
        editor.setUserPassword("editor1");

        memberService.registerMember(editor);

        Member customer  = new Member();
        customer.setUserType(Member.UserType.CUSTOMER);
        customer.setUserId("customer1");
        customer.setUserPassword("customer1");

        memberService.registerMember(customer);
    }

    @Test
    @Order(2)
    public void registerProductTest1(){
        Member writer  = new Member();
        writer.setUserType(Member.UserType.WRITER);
        writer.setUserId("writer1");
        writer.setUserPassword("writer1");



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
    @Order(3)
    public void registerProductTest2(){
        Member writer  = new Member();
        writer.setUserType(Member.UserType.WRITER);
        writer.setUserId("writer1");
        writer.setUserPassword("writer1");


        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductOwner(writer);
        productInfo.setPrice(150.0);

        Product product = new Product();
        product.setContents("Contents");
        product.setTitle("Title");
        product.setLanguage(Product.Language.ENG);
        product.setProductInfo(productInfo);

        productInfo.getProductList().add(product);
        productInfoService.registerProductInfo(productInfo);
    }

    @Test
    @Order(4)
    public void getProductInfoListTest1(){
        List<ProductListResponseDTO> productListDTO = productInfoService.getProductList(ProductInfo.Status.WAITING, Product.Language.KOR);

        for(ProductListResponseDTO iter : productListDTO){
            System.out.println(iter.getTitle() + " " + iter.getWriterId() + " " + iter.getPrice());
        }

    }
}
