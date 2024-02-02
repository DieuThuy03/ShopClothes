package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.repositories.MaterialRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MaterialService implements IService<Material> {

    @Autowired
    private MaterialRepo materielRepo;

    @Override
    public void save(Material object) {
        materielRepo.save(object);
    }

    @Override
    public void update(Material object, Long id) {
        materielRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        materielRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        materielRepo.findById(id).orElse(null);
    }

    @Override
    public List<Material> select(String status) {
        return materielRepo.findAll();
    }
}
