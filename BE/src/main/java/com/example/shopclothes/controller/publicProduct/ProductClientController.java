package com.example.shopclothes.controller.publicProduct;

import com.example.shopclothes.dto.ProductClientDTO;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/public/product")

public class ProductClientController {

    @Autowired
    private ProductService productService;

    @GetMapping("/display")
    public Page<ProductClientDTO> viewALl(@RequestParam(value = "page", defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<ProductClientDTO> listProduct = productService.search(pageable);
        return listProduct;
    }

}
