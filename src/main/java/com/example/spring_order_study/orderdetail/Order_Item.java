package com.example.spring_order_study.orderdetail;

import com.example.spring_order_study.item.Item;
import com.example.spring_order_study.order.Customer_Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Order_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderPrice;  // 주문가격

    private Long count; // 주문수량

    // 주문아이템 : 아이템 = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="item_id")
    private Item item;   // 주문아이템

    // 주문아이템 : 주문 = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="order_id")
    private Customer_Order customerOrder;   // 주문정보

    @Builder
    public Order_Item(Long orderPrice, Long count, Item item, Customer_Order customerOrder){

        this.orderPrice = orderPrice;
        this.count = count;
        this.item = item;
        this.customerOrder = customerOrder;

    }


}
