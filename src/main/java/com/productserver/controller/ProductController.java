package com.productserver.controller;


import com.productserver.domain.*;
import com.productserver.service.ProductInfoService;
import com.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@RestController
@SessionAttributes( names = {"member", "language", "status"})
public class ProductController {
    final private ProductInfoService productInfoService;
    final private ProductService productService;

    @GetMapping("/productList")
    public List<ProductResponseDTO> getProductList(@RequestParam Product.Language language, @RequestParam ProductInfo.Status status,
                                                   @ModelAttribute("language") Product.Language sLang, @ModelAttribute("status") ProductInfo.Status sStatus, Model model){
        if(language == null) language = sLang;
        else{
            model.addAttribute("language", language);
        }
        if(status == null) status = sStatus;
        else{
            model.addAttribute("status", status);
        }

        List<ProductResponseDTO> ret = productInfoService.getProductList(status, language);

        return ret;
    }

    @GetMapping("/OwnProductList")
    public List<ProductInfo> getProductList( @ModelAttribute("member") Member member ){
        List<ProductInfo> ret = null;

        if(member.getUserType() == Member.UserType.WRITER)
            ret = productInfoService.getProductListByWriter(member);

        if(member.getUserType() == Member.UserType.EDITOR)
            ret = productInfoService.getProductListByEditor(member);

        return ret;
    }

    @GetMapping("/productInfo")
    @Transactional
    public ProductInfo getProductInfo(@RequestParam Long productInfoId){
        return productInfoService.getProductInfo(productInfoId);
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

    @GetMapping("/product")
    public ProductResponseDTO getProduct(@RequestParam Long productId , Model model){
        ProductResponseDTO ret = productService.getProduct(productId);
        model.addAttribute("product" , ret);
        return ret;
    }

    @PatchMapping("/product")
    public void patchProduct(@RequestParam Long productSeq, @RequestBody Product product){
        productService.updateProduct(product);

    }
}
