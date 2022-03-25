package com.productserver.controller;


import com.productserver.domain.*;
import com.productserver.service.ProductInfoService;
import com.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@RestController
@SessionAttributes( names = {"member"})
public class ProductController {
    final private ProductInfoService productInfoService;
    final private ProductService productService;

    @GetMapping("/productList")
    public List<ProductResponseDTO> getProductList(@RequestParam @Nullable Product.Language language, @RequestParam @Nullable ProductInfo.Status status,
                                                   @ModelAttribute("member") Member member, Model model){
        if(language == null) language = Product.Language.KOR;

            // Editor를 제외한 경우에는 SELLING 상태의 제품만 확인 가능
        if( member == null || member.getUserType() != Member.UserType.EDITOR ){
            status = ProductInfo.Status.SELLING;
        }
        else{
            if(status == null) status = ProductInfo.Status.SELLING;
            else{
                model.addAttribute("status", status);
            }
        }

        List<ProductResponseDTO> ret = productInfoService.getProductList(status, language);
        for (ProductResponseDTO iter : ret)
            System.out.println(iter.getPrice());
        return ret;
    }

    @GetMapping("/product")
    public ProductResponseDTO getProduct(@RequestParam Long productId){
        ProductResponseDTO ret = productService.getProduct(productId);

        return ret;
    }

    @GetMapping("/productInfo")
    @Transactional
    public ProductInfo getProductInfo(@RequestParam Long productInfoId){
        return productInfoService.getProductInfo(productInfoId);
    }

}
