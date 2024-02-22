package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Product;
import com.example.shopclothes.repositories.ProducerRepo;
import com.example.shopclothes.repositories.ProductRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService implements IService<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void save(Product object) {
        productRepo.save(object);
    }

    @Override
    public void update(Product object, Long id) {
        productRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        productRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> select() {
        return productRepo.findAll();
    }
}
