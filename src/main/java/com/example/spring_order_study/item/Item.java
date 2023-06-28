package com.example.spring_order_study.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    public void updateItem(Long price, Long stockQuantity){
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @Builder
    public Item(String name, Long price, Long stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;

    }


    public void removeQuantity(Long stockQuantity) throws Exception {
        Long new_quantity = this.stockQuantity - stockQuantity;
        this.stockQuantity = new_quantity;
        if(new_quantity<0){
            throw new Exception();
        }
    }

    public void AddQuantity(Long stockQuantity) {
        Long new_quantity = this.stockQuantity + stockQuantity;
        this.stockQuantity = new_quantity;
    }


}