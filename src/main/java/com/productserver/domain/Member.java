package com.productserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString


public class Member {

    public enum UserType{
        WRITER,
        EDITOR,
        CUSTOMER
    }

    @Id @Column( nullable = false)
    private String userId;

    @Column( nullable = false)
    private String userPassword;

    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}