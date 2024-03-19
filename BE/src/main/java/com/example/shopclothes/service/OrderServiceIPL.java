package com.example.shopclothes.service;

import com.example.shopclothes.dto.OrderInStoreRequestDto;
import com.example.shopclothes.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceIPL {
    public Order createOrderInStore();
    public List<Order> findAllOrderByStatusName();

    public Boolean DeleteOrder(Long id);

    public Order findOrderById(Long id);

    public Order updateOrder(OrderInStoreRequestDto requestDto);

    public Order updateOrderUser(Long orderId, Long userId);

    public Page<Order> getAllOrders(String orderStatusName, String orderId, String orderType, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
