package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Imege;
import com.example.shopclothes.repositories.ImageRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ImageService implements IService<Imege> {

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public void save(Imege object) {
        imageRepo.save(object);
    }

    @Override
    public void update(Imege object, Long id) {
        imageRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        imageRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        imageRepo.findById(id).orElse(null);
    }

    @Override
    public List<Imege> select() {
        return imageRepo.findAll();
    }
}
