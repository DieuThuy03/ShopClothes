package com.example.shopclothes.controller;

import com.example.shopclothes.entity.PaymentDetail;
import com.example.shopclothes.service.impl.PaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PaymentDetail")

public class PaymentDetailController {

    @Autowired
    private PaymentDetailService paymentsDetailService;

    @GetMapping("/hien-thi/{status}")
    public List<PaymentDetail> hienThi(@PathVariable String status){
        return paymentsDetailService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        paymentsDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        paymentsDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(PaymentDetail paymentsDetail){
        paymentsDetailService.save(paymentsDetail);
    }

    @PostMapping("/update/{id}")
    public void update(PaymentDetail paymentsDetail, @PathVariable Long id){
        paymentsDetailService.update(paymentsDetail, id);
    }
}
