package com.example.spring_order_study.orderhistory;

import com.example.spring_order_study.item.Item;
import com.example.spring_order_study.member.Member;
import com.example.spring_order_study.order.OrderStatus;
import com.example.spring_order_study.orderdetail.Order_Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Order_History {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;  // 주문수량

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="member_id")
    private Member member;

    // Order_Item 조회해오기
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<Order_Item> orderItems = new ArrayList<>();



    @Builder
    public Order_History(Long count, OrderStatus status, LocalDateTime orderDate, Item item, Member member){

        this.count = count;
        this.status = status;
        this.orderDate = orderDate;
        this.item = item;
        this.member = member;


    }







}
