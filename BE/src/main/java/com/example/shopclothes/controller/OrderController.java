package com.example.shopclothes.controller;


import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.OrderInStoreRequestDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.entity.Order;
import com.example.shopclothes.service.impl.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getAllOrderByStatusName")
    public ResponseEntity<List<Order>> getAllOrderByStatusName() {
        List<Order> orderList = orderService.findAllOrderByStatusName();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @DeleteMapping("delete")
    public  ResponseEntity<ResponseDto> deleteOrder(@RequestParam Long id) {
        Boolean isDelete = orderService.DeleteOrder(id);
        if (isDelete){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200, NotificationConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }

    @GetMapping("findOrderById")
    public ResponseEntity<Order> findOrderById(@RequestParam Long id) {

        Order order = orderService.findOrderById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }

    @PutMapping("updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderInStoreRequestDto requestDto) {
        Order  order = orderService.updateOrder(requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(order);
    }
}
