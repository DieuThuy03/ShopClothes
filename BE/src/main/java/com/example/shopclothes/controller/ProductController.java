package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Product;
import com.example.shopclothes.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hien-thi/{status}")
    public List<Product> hienThi(@PathVariable String status){
        return productService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        productService.search(id);
    }

    @PostMapping("/add")
    public void add(Product product){
        productService.save(product);
    }

    @PostMapping("/update/{id}")
    public void update(Product product, @PathVariable Long id){
        productService.update(product,id);
    }
}
