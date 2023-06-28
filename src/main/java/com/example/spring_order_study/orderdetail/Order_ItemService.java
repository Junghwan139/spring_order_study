package com.example.spring_order_study.orderdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class Order_ItemService {

    @Autowired
    Order_ItemRepository orderItemRepository;


    // create
    public void orderItemSave(Order_ItemDto orderItemDto){

        Order_Item orderItems = Order_Item.builder()
                .orderPrice(orderItemDto.getItemId().getPrice())
                .count(orderItemDto.getCount())
                .item(orderItemDto.getItemId())
                .customerOrder(orderItemDto.getCustomerOrder())
                .build();
        orderItemRepository.save(orderItems);
    }


    // read_all
    public List<Order_Item> order_find_all(){

        List<Order_Item> orderItems = orderItemRepository.findAll();
        return orderItemRepository.findAll();
    }

    //read_one
    public Order_Item order_find_one(Long id){
        return orderItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



}
