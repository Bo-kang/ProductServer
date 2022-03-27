package com.productserver.controller;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.domain.ProductRequestDTO;
import com.productserver.service.ProductInfoService;
import com.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* Login이 완료된 사용자를 위한 기능 모음 Controller
* */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SessionAttributes( names = {"member"})
public class AuthorizedProductController {
    final private ProductInfoService productInfoService;
    final private ProductService productService;

    @GetMapping("/productInfo")
    @Transactional
    public ProductInfo getProductInfo(@RequestParam Long productInfoId){
        return productInfoService.getProductInfo(productInfoId);
    }

    @GetMapping("/productList")
    public List<ProductInfo> getProductInfoList(@ModelAttribute("member") Member member ){
        List<ProductInfo> ret = null;

        if(member.getUserType() == Member.UserType.WRITER)
            ret = productInfoService.getProductListByWriter(member);

        if(member.getUserType() == Member.UserType.EDITOR)
            ret = productInfoService.getProductListByEditor(member);

        return ret;
    }

    @PostMapping("/productInfo")
    public ProductInfo registerProductInfo(ProductRequestDTO productDTO, @ModelAttribute("member") Member member){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductOwner(member);
        productInfo.setPrice(productDTO.getPrice());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setLanguage(productDTO.getLanguage());
        product.setContents(productDTO.getContents());
        product.setProductInfo(productInfo);

        productInfo.getProductList().add(product);

        return productInfoService.registerProductInfo(productInfo);
    }


    @PatchMapping("/productInfo/{productId}")
    public void patchProductInfo(@ModelAttribute("member") Member member,@RequestParam Long productId , @RequestBody ProductInfo productInfo){

        productInfo.setProductId(productId);

        if(member.getUserType().equals(Member.UserType.EDITOR)){
            productInfo.setEditor(member);
        }

        productInfoService.updateProductInfo(productInfo);

    }

    @PostMapping("/product")
    public Product insertProduct(@RequestBody Product product ){
        return productService.registerProduct(product);
    }


    @PatchMapping("/product")
    public void patchProduct(@RequestParam Long productSeq, @RequestBody Product product){
        productService.updateProduct(product);

    }
}
