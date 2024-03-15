package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.orderStatus.statusName = 'Tạo đơn hàng' ")
    List<Order> findAllOrderByStatusName();
}