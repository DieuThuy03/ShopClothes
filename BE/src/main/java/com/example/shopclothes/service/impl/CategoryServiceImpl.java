package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.CategoryRepo;
import com.example.shopclothes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void update(Category category, Long id) {
        categoryRepo.save(category);
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
    public Page<Category> select(Status status, Pageable pageable) {
        return categoryRepo.getAllByStatus(status, pageable);
    }

}
