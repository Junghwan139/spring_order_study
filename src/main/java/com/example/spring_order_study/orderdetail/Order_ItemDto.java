package com.example.spring_order_study.orderdetail;

import com.example.spring_order_study.item.Item;
import com.example.spring_order_study.order.Customer_Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order_ItemDto {


    private Long orderPrice;
    private Long count;
    private Item itemId;
    private Customer_Order customerOrder;


}
