package com.example.spring_order_study.member;

import com.example.spring_order_study.order.Customer_Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    private String name;

    private String email;

    private String password;

    @Embedded
    private Address address;

    // Memeber를 조회할 때, Member의 id값을 가지고, Orders의 어떤 컬럼을 where조건을 주고
    // 조회할지에 대해서 mapping정보를 Member테이블에 알려주는 것
    // OneToMany 또는 ManyToOne을 할 때 fetch전략이라는게 있다. Memeber객체 입장에서 fetch전략은 즉시 Order객체를 조회할지 말지에 대한 선택
    // LAZY 즉시 로딩x -> 참조해서 사용할 때만 로딩o, EAGER 즉시 로딩o
    // N+1 이슈를 해결하기 위해서는 LAZY 사용 추천
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Customer_Order> customerOrders;

    @Column
    private LocalDateTime createDate;

    @Builder
    public Member(String name, String email, String password, Address address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createDate = LocalDateTime.now();

    }



}
