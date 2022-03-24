package com.productserver.controller;


import com.productserver.domain.*;
import com.productserver.service.MemberService;
import com.productserver.service.ProductInfoService;
import com.productserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;


@RequiredArgsConstructor
@Controller
@SessionAttributes( names = {"member"})
public class ProductController {
    final private ProductInfoService productInfoService;
    final private ProductService productService;

    @GetMapping("/insertProduct")
    public void insertProductView(){

    }

    @GetMapping("/myProductList")
    public String getMyProductList(@ModelAttribute("member") Member member, Model model){
        List<ProductInfo> ret = productInfoService.getProductListByWriter(member);
        System.out.println(ret);
        model.addAttribute("productInfoList" , ret);

        return "myProductList";
    }

    @GetMapping("/chargedProductList")
    public String getChargedProductList(@ModelAttribute("member") Member member, Model model){
        List<ProductInfo> ret = productInfoService.getProductListByEditor(member);
        System.out.println(ret);
        model.addAttribute("productInfoList" , ret);

        return "chargedProductList";
    }

    @GetMapping("/chargedProduct")
    @Transactional
    public String getChargedProduct(@RequestParam Long productInfoId, Model model){
        ProductInfo ret = productInfoService.getProductInfo(productInfoId);
        System.out.println(ret);
        model.addAttribute("chargedProductInfo", ret);
        return "chargedProduct";
    }

    @PostMapping("/productInfo")
    public String registerProductInfo(ProductRequestDTO productDTO, @ModelAttribute("member") Member member){

        System.out.println(productDTO);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductOwner(member);
        productInfo.setPrice(productDTO.getPrice());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setLanguage(productDTO.getLanguage());
        product.setContents(productDTO.getContents());
        product.setProductInfo(productInfo);

        productInfo.getProductList().add(product);

        System.out.println(productInfo);
        productInfoService.registerProductInfo(productInfo);

        return "redirect:productList";
    }

    @GetMapping("/productList")
    public String getProductList( Product.Language language, ProductInfo.Status status, Model model){
        if(language == null) language = Product.Language.KOR;
        if(status == null) status = ProductInfo.Status.SELLING;
        if(model.getAttribute("language") != null) language = (Product.Language)model.getAttribute("language");
        if(model.getAttribute("status") != null) status = (ProductInfo.Status)model.getAttribute("status");

        List<ProductResponseDTO> ret = productInfoService.getProductList(status, language);

        model.addAttribute("productList", ret);
        model.addAttribute("language", language);
        model.addAttribute("status", status);

        return "productList";
    }


    @GetMapping("/product")
    public String getProduct(@RequestParam Long productId , Model model){
        ProductResponseDTO ret = productService.getProduct(productId);
        model.addAttribute("product" , ret);
        return "product";
    }

    @PatchMapping("/productInfo")
    public String patchProductInfo(@ModelAttribute("member") Member member, @RequestParam Long productId, @RequestBody ProductInfo productInfo){

        if(member.getUserType().equals(Member.UserType.EDITOR)){
            productInfo.setEditor(member);
        }

        productInfoService.updateProductInfo(productInfo);

        return "myProductList";
    }

    @PatchMapping("/product")
    public String patchProduct(@RequestParam Long productSeq, @RequestBody Product product){

        productService.updateProduct(product);
        System.out.println(product);
        return "myProductList";
    }

    @PostMapping("/product")
    public String insertProduct(@RequestBody Product product , HttpServletRequest req){
        System.out.println(product);
        System.out.println(product.getProductInfo());
        productService.registerProduct(product);

        return "redirect:"+req.getHeader("Referer");
    }
}
