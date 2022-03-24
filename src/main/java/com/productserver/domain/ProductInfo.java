package com.productserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class ProductInfo {

    public enum Status{
        WAITING,
        WAITING_CONSIDER,
        UNDER_CONSIDER,
        SELLING,
        REFUSED,
        SUSPENDED
    }

    @Id @Column( nullable = false)
    @GeneratedValue
    private Long productId;

    @ManyToOne @JoinColumn(name = "owner_id", nullable = false)
    private Member productOwner;

    @Enumerated(EnumType.STRING) @Column(insertable = false , columnDefinition = " varchar(30) default 'WAITING'")
    private Status productStatus;

    @Column(insertable = false , columnDefinition = " date default sysdate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(insertable = false , columnDefinition = " date default sysdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne @JoinColumn(name = "editor_id")
    private Member editor;

    @Column(nullable = false)
    private Double price;

    @Column(insertable = false , columnDefinition = " double default 0")
    private Double fee;

    @OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

}