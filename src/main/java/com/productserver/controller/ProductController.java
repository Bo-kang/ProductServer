package com.productserver.controller;


import com.productserver.domain.*;
import com.productserver.service.MemberService;
import com.productserver.service.ProductInfoService;
import com.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class ProductController {
    final private ProductInfoService productInfoService;
    final private ProductService productService;
    final private MemberService memberService;
    @PostMapping("/productInfo")
    public ProductInfo registerProductInfo(@RequestBody ProductRequestDTO productDTO, @ModelAttribute("member") Member member){

        System.out.println(productDTO);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productDTO.getProductId());
        productInfo.setProductOwner(member);
        productInfo.setPrice(productDTO.getPrice());
        productInfo.setFee(productDTO.getFee());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setLanguage(productDTO.getLanguage());
        product.setContents(productDTO.getContents());
        product.setProductInfo(productInfo);

        System.out.println(productInfo);
        System.out.println(product);

        productInfo.getProductList().add(product);
        return productInfoService.registerProductInfo(productInfo);


    }

    @PostMapping("/product")
    public Product registerProduct(@ModelAttribute("member") Member member, @RequestBody ProductInfo productInfo, @RequestBody Product product){
        product.setProductInfo(productInfo);
        Product ret = productService.registerProduct(product);

        return ret;
    }

    @PutMapping("/productInfo")
    public void updateProductInfo(@ModelAttribute("member") Member member, @RequestBody ProductInfo productInfo){
        productInfo.setEditorId(member);
        productInfoService.updateProductInfo(productInfo);
    }

    @GetMapping("/productList")
    public String getProductList(Product.Language language, ProductInfo.Status status, Model model){
        if(language == null) language = Product.Language.KOR;
        if(status == null) status = ProductInfo.Status.SELLING;

        List<ProductResponseDTO> ret = productInfoService.getProductList(status, language);
        model.addAttribute("productList", ret);

        return "productList";
    }


    @GetMapping("/product")
    public ProductResponseDTO getProduct(@RequestParam Long productId){
        ProductResponseDTO ret = productService.getProduct(productId);

        return ret;
    }
}
