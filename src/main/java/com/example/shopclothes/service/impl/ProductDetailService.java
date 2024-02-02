package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.repositories.PaymentDetailRepo;
import com.example.shopclothes.repositories.ProductDetailRepo;
import com.example.shopclothes.repositories.ProductRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductDetailService implements IService<ProductDetail> {

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Override
    public void save(ProductDetail object) {
        productDetailRepo.save(object);
    }

    @Override
    public void update(ProductDetail object, Long id) {
        productDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        productDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        productDetailRepo.findById(id);
    }

    @Override
    public List<ProductDetail> select(String status) {
        return productDetailRepo.findAll();
    }
}
