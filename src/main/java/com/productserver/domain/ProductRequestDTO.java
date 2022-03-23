package com.productserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProductRequestDTO {
    private Long productInfoId;
    private Long productId;
    private String writerId;
    private Date createDate;
    private String title;
    private Double price;
    private Double fee;
    private Product.Language language;
    private String contents;
}
