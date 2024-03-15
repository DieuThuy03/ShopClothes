package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.OrderInStoreRequestDto;
import com.example.shopclothes.entity.*;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.OrderDetailRepository;
import com.example.shopclothes.repositories.OrderHistoryRepository;
import com.example.shopclothes.repositories.OrderRepository;
import com.example.shopclothes.repositories.OrderStatusRepository;
import com.example.shopclothes.service.OrderServiceIPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderServiceIPL {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

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

    @Override
    public List<Order> findAllOrderByStatusName() {
        return orderRepository.findAllOrderByStatusName();
    }

    @Override
    public Boolean DeleteOrder(Long id){

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn này"));

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(id);

        for (OrderDetail orderDetail : orderDetails){
            orderDetailRepository.delete(orderDetail);
        }

        OrderHistory orderHistory = orderHistoryRepository.findByOrderId(order.getId());
        orderHistoryRepository.delete(orderHistory);
        // Xóa hóa đơn
        orderRepository.delete(order);
        return  true;

    }

    @Override
    public Order findOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này"));

        return order;
    }

    @Override
    public Order updateOrder(OrderInStoreRequestDto requestDto) {
        // Tìm đối tượng Order theo ID
        Order order = orderRepository.findById(requestDto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này!"));

        // Tìm đối tượng OrderStatus
        OrderStatus orderStatus = (requestDto.getStatusName() != null) ? orderStatusRepository.findByStatusName(requestDto.getStatusName()).orElse(null) : null;

        // Kiểm tra xem có ngoại lệ nào ném ra hay không
        boolean hasException = false;

        // Kiểm tra số lượng sản phẩm chi tiết trong đơn hàng
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            ProductDetail productDetail = orderDetail.getProductDetail();

            if (productDetail.getQuantity() < orderDetail.getQuantity()) {
                hasException = true;
                // Số lượng sản phẩm chi tiết vượt quá số lượng trong kho
                throw new ResourceNotFoundException("Số lượng sản phẩm vượt quá số lượng trong kho!");
            }
        }

        // Nếu có ngoại lệ, không tiến hành cập nhật hóa đơn
        if (!hasException) {
            // Cập nhật thông tin của hóa đơn
            order.setOrderStatus(orderStatus);
            order.setOrderTotal(requestDto.getOrderTotal());
            order.setNote(requestDto.getNote());
            order.setTransportFee(requestDto.getTransportFee());
            // Địa chỉ giao
            order.setRecipientName(requestDto.getRecipientName());
            order.setPhoneNumber(requestDto.getPhoneNumber());
            order.setAddressDetail(requestDto.getAddressDetail());
            order.setWard(requestDto.getWard());
            order.setDistrict(requestDto.getDistrict());
            order.setCity(requestDto.getCity());

            // Lưu hóa đơn vào cơ sở dữ liệu
            orderRepository.save(order);

            // Tạo đối tượng OrderHistory và lưu vào cơ sở dữ liệu
            OrderHistory timeLine = new OrderHistory();
            timeLine.setOrder(order);
            timeLine.setNote(requestDto.getNote());
            timeLine.setStatus(orderStatus);
            orderHistoryRepository.save(timeLine);
        }
        return order;
    }
}
