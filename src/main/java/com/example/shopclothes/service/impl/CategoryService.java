package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.repositories.CategoryRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService implements IService<Category> {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void save(Category object) {
        categoryRepo.save(object);
    }

    @Override
    public void update(Category object, Long id) {
        categoryRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        categoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<Category> select(String status) {
        return categoryRepo.select(status);
    }
}
