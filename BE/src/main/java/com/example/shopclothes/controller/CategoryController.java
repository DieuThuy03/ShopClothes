package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Category")

public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> hienThi(@RequestParam(defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return ResponseEntity.ok(categoryService.select(Status.DANG_HOAT_DONG, pageable)) ;

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
    public void add(@RequestBody Category category){
        String code = "CT" + new Random().nextInt(100000);
        category.setCode(code);
        category.setDateCreate(new Date());
        categoryService.save(category);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Category category, @PathVariable Long id){
        categoryService.update(category, id);
    }

}
