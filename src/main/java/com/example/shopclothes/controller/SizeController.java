package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Size;
import com.example.shopclothes.service.impl.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Size")

public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/hien-thi")
    public List<Size> hienThi(){
        return sizeService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        sizeService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        sizeService.search(id);
    }

    @PostMapping("/add")
    public void add(Size size){
        sizeService.save(size);
    }

    @PostMapping("/update/{id}")
    public void update(Size size, @PathVariable Long id){
        sizeService.update(size, id);
    }
}
