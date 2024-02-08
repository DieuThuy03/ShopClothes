package com.example.shopclothes.controller;

import com.example.shopclothes.entity.PaymentDetail;
import com.example.shopclothes.service.impl.PaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Payments")

public class PaymentsController {

    @Autowired
    private PaymentDetailService paymentDetailService;

    @GetMapping("/hien-thi/{status}")
    public List<PaymentDetail> Select(@PathVariable String status){
        return paymentDetailService.select(status);
    }

    @GetMapping("/delete/{id}")
    private void delete(@PathVariable Long id){
        paymentDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        paymentDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(PaymentDetail paymentDetail){
        paymentDetailService.save(paymentDetail);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable Long id, PaymentDetail paymentDetail){
        paymentDetailService.update(paymentDetail, id);
    }
}
