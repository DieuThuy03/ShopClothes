package com.example.shopclothes.service;

import com.example.shopclothes.dto.OrderInStoreRequestDto;
import com.example.shopclothes.entity.Order;

import java.util.List;

public interface OrderServiceIPL {
    public Order createOrderInStore();
    public List<Order> findAllOrderByStatusName();

    public Boolean DeleteOrder(Long id);

    public Order findOrderById(Long id);

    public Order updateOrder(OrderInStoreRequestDto requestDto);
}