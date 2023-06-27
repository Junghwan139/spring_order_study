package com.example.spring_order_study.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

// 별도의 테이블로 존재하지 않고, 다른 entity에 첨부되는 것.
// Embedded와 한쌍
@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String zipcode;
    private String street;

    public Address (String city, String street, String zipcode ){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
