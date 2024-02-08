package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Imege;
import com.example.shopclothes.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Image")

public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/hien-thi/{status}")
    public List<Imege> hienThi(@PathVariable String status){
        return imageService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        imageService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        imageService.search(id);
    }

    @PostMapping("/add")
    public void add(Imege imege){
        imageService.save(imege);
    }

    @PostMapping("/update/{id}")
    public void update(Imege imege, @PathVariable Long id){
        imageService.update(imege, id);
    }
}
