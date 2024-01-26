package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/hien-thi")
    public List<Category> hienThi(){
        return categoryService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        categoryService.search(id);
    }

    @PostMapping("/add")
    public void add(Category category){
        categoryService.save(category);
    }

    @PostMapping("/update/{id}")
    public void update(Category category, @PathVariable Long id){
        categoryService.update(category, id);
    }

}
