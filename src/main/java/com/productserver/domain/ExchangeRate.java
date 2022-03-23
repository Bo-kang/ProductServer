package com.productserver.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@Entity
public class ExchangeRate {
    @Id @GeneratedValue
    private Long seq;

    @Column(nullable = false, columnDefinition = "date default sysdate")
    private Date standardDate;

    @Column(nullable = false)
    private Product.Language country;

    @Column(nullable = false)
    private Double per1KRW;
}