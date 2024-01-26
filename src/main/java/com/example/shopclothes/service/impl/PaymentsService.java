package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Payments;
import com.example.shopclothes.repositories.PaymentsRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentsService implements IService<Payments> {

    @Autowired
    private PaymentsRepo paymentsRepo;

    @Override
    public void save(Payments object) {
        paymentsRepo.save(object);
    }

    @Override
    public void update(Payments object, Long id) {
        paymentsRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        paymentsRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        paymentsRepo.findById(id).orElse(null);
    }

    @Override
    public List<Payments> select() {
        return paymentsRepo.findAll();
    }
}
