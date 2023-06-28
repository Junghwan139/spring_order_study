package com.example.spring_order_study.order;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Customer_OrderDto {

    private Long id;
    private List<Long> count;
    private String status;
    private String memberId;
    private List<String> itemId;


}
