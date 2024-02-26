package com.example.shopclothes.service;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void save(Category category);

    void update(Category category, Long id);

    void delete(Long id);

    void search(Long id);

    Page<Category> select(Status status, Pageable pageable);
}
