package com.productserver.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@Entity
public class ExchangeRate {
    @Id
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Product.Language country;

    @Column(nullable = false)
    private Double per1KRW;
}