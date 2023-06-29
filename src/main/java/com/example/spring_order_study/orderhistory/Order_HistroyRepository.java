package com.example.spring_order_study.orderhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface Order_HistroyRepository extends JpaRepository<Order_History, Long> {

}
