package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Order;
import com.example.shopclothes.entity.OrderHistory;
import com.example.shopclothes.entity.OrderStatus;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.OrderHistoryRepository;
import com.example.shopclothes.repositories.OrderRepository;
import com.example.shopclothes.repositories.OrderStatusRepository;
import com.example.shopclothes.service.OrderServiceIPL;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceIPL {

    private OrderStatusRepository orderStatusRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private OrderRepository orderRepository;


    @Override
    public Order createOrderInStore() {
        OrderStatus orderStatus = orderStatusRepository.findByStatusName("Tạo đơn hàng").orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy trạng thái hóa đơn này!"));
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        order.setOrderType("Tại quầy");
        orderRepository.save(order);
        OrderHistory timeLine = new OrderHistory();
        timeLine.setOrder(order);
        timeLine.setStatus(orderStatus);
        orderHistoryRepository.save(timeLine);

        return order;
    }

}
