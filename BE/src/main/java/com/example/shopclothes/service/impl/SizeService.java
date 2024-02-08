package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Size;
import com.example.shopclothes.repositories.SizeRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SizeService implements IService<Size> {

    @Autowired
    private SizeRepo sizeRepo;

    @Override
    public void save(Size object) {
        sizeRepo.save(object);
    }

    @Override
    public void update(Size object, Long id) {
        sizeRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        sizeRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        sizeRepo.findById(id).orElse(null);
    }

    @Override
    public List<Size> select(String status) {
        return sizeRepo.findAll();
    }
}
