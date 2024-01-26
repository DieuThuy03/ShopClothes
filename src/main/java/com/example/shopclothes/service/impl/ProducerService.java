package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.repositories.ProducerRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProducerService implements IService<Producer> {

    @Autowired
    private ProducerRepo producerRepo;

    @Override
    public void save(Producer object) {
        producerRepo.save(object);
    }

    @Override
    public void update(Producer object, Long id) {
        producerRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        producerRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        producerRepo.findById(id).orElse(null);
    }

    @Override
    public List<Producer> select() {
        return producerRepo.findAll();
    }
}
