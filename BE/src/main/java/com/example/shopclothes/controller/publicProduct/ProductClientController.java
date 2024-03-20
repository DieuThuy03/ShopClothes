package com.example.shopclothes.controller.publicProduct;

import com.example.shopclothes.dto.ProductClientDTO;
import com.example.shopclothes.dto.ProductDTONamePrice;
import com.example.shopclothes.dto.ProductDetailMate;
import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ProductRepo;
import com.example.shopclothes.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/public/product")

public class ProductClientController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/display")
    public Page<ProductClientDTO> viewALl(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "key", required = false) String key){
        Pageable pageable = PageRequest.of(page, 8);
        Page<ProductClientDTO> listProduct = productService.search(pageable, key);
        return listProduct;
    }

    @GetMapping("/display2")
    public Page<ProductClientDTO> viewALl1(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "key", required = false) String key){
        Pageable pageable = PageRequest.of(page, 24);
        Page<ProductClientDTO> listProduct = productService.search(pageable, key);
        return listProduct;
    }

//    @GetMapping("/display")
//    public ResponseEntity<?> viewAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(value = "key", required = false) String key){
//        Pageable pageable = PageRequest.of(page, 4);
//        Page<ProductClientDTO> productClientDTOS = productRepo.search(pageable, key);
//        return ResponseEntity.ok(productClientDTOS.getContent());
//    }

    @GetMapping("filterProductByNamePrice")
    public ResponseEntity<List<ProductDTONamePrice>> filterProductDetailByMate(@RequestParam Long idProduct){
        List<ProductDTONamePrice> sizeProductList = productService.fillterProductByNamePrice(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(sizeProductList);
    }



}
