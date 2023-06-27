package com.example.spring_order_study.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemService {

    @Autowired ItemRepository itemRepository;

    // create
    public void item_save(ItemDto item){

        Item item1 = Item.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();
        itemRepository.save(item1);

    }

    // read_all
    public List<Item> item_findall(){
        return itemRepository.findAll();
    }

    // read_one
    public Item item_one(Long id){
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // update
    public void update(ItemDto item) throws Exception{
        Item item1 = itemRepository.findById(item.getId()).orElseThrow(EntityNotFoundException::new);

        if(item1 == null){
            throw new Exception();
        }else{

           item1.updateItem(item.getPrice(),item.getStockQuantity());
           itemRepository.save(item1);

        }

    }

    public void ItemDelete(Long id){
        itemRepository.delete(this.item_one(id));
    }




}
