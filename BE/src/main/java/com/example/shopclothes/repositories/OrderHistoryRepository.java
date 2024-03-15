package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long> {

    OrderHistory findByOrderId(Long id);
}