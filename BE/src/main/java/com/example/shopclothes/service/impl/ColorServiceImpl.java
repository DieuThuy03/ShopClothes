package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ColorRepo;
import com.example.shopclothes.service.ColorService;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepo colorRepo;

    @Override
    public void save(Color color) {
        colorRepo.save(color);
    }

    @Override
    public void update(Color color, Long id) {
        colorRepo.save(color);
    }

    @Override
    public void delete(Long id) {
        colorRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        colorRepo.findById(id).orElse(null);
    }

//    @Override
//    public List<Color> select() {
//        return colorRepo.findAll();
//    }

    public Page<Color> select(Status status, Pageable pageable) {
        return colorRepo.getAllByStatus(status, pageable);
    }
}
