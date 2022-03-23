package com.productserver.domain;


import java.util.Date;


public interface ProductResponseDTO {

    Long getProductInfoId();
    Long getProductId();
    String getWriterId();
    Date getCreateDate();
    String getTitle();
    Double getPrice();
    Double getFee();
    Product.Language getLanguage();
    String getContents();
}
