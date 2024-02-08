package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.PaymentDetail;
import com.example.shopclothes.repositories.PaymentDetailRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentDetailService implements IService<PaymentDetail> {

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Override
    public void save(PaymentDetail object) {
        paymentDetailRepo.save(object);
    }

    @Override
    public void update(PaymentDetail object, Long id) {
        paymentDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        paymentDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        paymentDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<PaymentDetail> select(String status) {
        return paymentDetailRepo.findAll();
    }
}
