package com.productserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"productInfo"})
@Entity
@DynamicInsert
public class Product {

    public enum Language{
        KOR,
        CHN,
        ENG
    }

    @Id @GeneratedValue
    @Column(updatable = false, insertable = false)
    private Long seq;

    @ManyToOne @JoinColumn(name = "product_id")
    private ProductInfo productInfo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Enumerated(EnumType.STRING) @Column( nullable = false, columnDefinition = "varchar(20) default 'KOR'")
    private Language language;

}