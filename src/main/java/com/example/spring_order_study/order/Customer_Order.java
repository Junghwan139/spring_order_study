package com.example.spring_order_study.order;

import com.example.spring_order_study.item.Item;
import com.example.spring_order_study.member.Member;
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
public class Customer_Order {


    //  주문(order) : id, member_id, item_id, 주문수량, 주문상태, item, member
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long count;  // 주문수량

    //Enumtupe.string을 주지 않으면 DB에 순서 숫자가 들어가게 된다
    // 즉 1, 2 등 숫자 값이 디폴트
    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // 상태

    private LocalDateTime orderDate;  // 주문일자

    // 주문 : 아이템 = N : 1
    //Item FK     상품명 -> item_id로 대체가능
    @ManyToOne(fetch = FetchType.LAZY)    // @OneToOne    1:N -> author와 post(FK), 1:1 ->
    @JoinColumn(nullable = false, name="item_id") // FK 아무쪽에나 걸 수 있으나, 거는 쪽에 Id가 걸림
    private Item item;


    // Member FK       member와 orders와 관계, orders findById조회 -> orders에 컬럼중에 member_id가 있네?
    // ManyToOne걸려 있네? member_id로 member테이블 조회
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="member_id")
    private Member member;


    // Order_Item 조회해오기
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<Order_Item> orderItems = new ArrayList<>();


    // status_change
    public void status_Change(){
        this.status = OrderStatus.CANCELED;
    }


    @Builder
    public Customer_Order(Long count, String status, Item item1, Member member1) throws Exception {

        this.count = count;
        this.status = OrderStatus.ORDER;
        this.item = item1;
        // Orders 객체 안에 Item객체를 OneToOne으로 가지고 있기 때문에, item객체에 quantity를
        // 변경 시키는 removeQuantity를 호출하고, Orders만 save하여도 Item테이블에 item객체가 변경이 된다.
        // jpa가 order를 building했을 때, item테이블의 값을 임시 저장하고 있다가 order를 save할 때, item테이블도 같이 save(update)
        this.item.removeQuantity(count);
        this.member = member1;
        this.orderDate = LocalDateTime.now();

    }


}
