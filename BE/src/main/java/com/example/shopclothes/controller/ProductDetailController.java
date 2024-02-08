package com.example.shopclothes.controller;

import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.service.impl.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductDetail")

public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/hien-thi/{status}")
    public List<ProductDetail> hienThi(@PathVariable String status){
        return productDetailService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        productDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(ProductDetail productDetail){
        productDetailService.save(productDetail);
    }

    @PostMapping("/update/{id}")
    public void update(ProductDetail productDetail, @PathVariable Long id){
        productDetailService.update(productDetail, id);
    }
}
