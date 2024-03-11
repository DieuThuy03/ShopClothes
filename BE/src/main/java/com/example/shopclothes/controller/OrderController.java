package com.example.shopclothes.controller;


import com.example.shopclothes.entity.Order;
import com.example.shopclothes.service.impl.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/orders/")
@Tag(name = "Orders", description = "( Rest API Hiển thị, thêm, sửa, phân trang, tìm kiếm, lọc hóa đơn )")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("createOrderInStore")
    public ResponseEntity<Order> createOrderInStore() {
        Order  order = orderService.createOrderInStore();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(order);
    }
}
