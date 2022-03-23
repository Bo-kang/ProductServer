package com.productserver.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface ProductListResponseDTO {

    Long getProductInfoId();
    Long getProductId();
    String getWriterId();
    Date getCreateDate();
    String getTitle();
    Double getPrice();
    Double getFee();
    Product.Language getLanguage();

}
