package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.repositories.ColorRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ColorService implements IService<Color> {

    @Autowired
    private ColorRepo colorRepo;

    @Override
    public void save(Color object) {
        colorRepo.save(object);
    }

    @Override
    public void update(Color object, Long id) {
        colorRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        colorRepo.deleteById(id);
    }

    @Override
    public void search(Long id) {
        colorRepo.findById(id).orElse(null);
    }

    @Override
    public List<Color> select(String status) {
        return colorRepo.findAll();
    }
}
