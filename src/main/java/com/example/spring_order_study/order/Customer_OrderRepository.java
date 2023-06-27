package com.example.spring_order_study.order;

import com.example.spring_order_study.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Customer_OrderRepository extends JpaRepository<Customer_Order, Long> {

    //findByA를 하면 A컬럼을 where조건으로 넣는 것.

    List<Customer_Order> findByMember(Member member);

    List<Customer_Order> findByMemberAndStatus(Member member, OrderStatus orderStatus);

}
