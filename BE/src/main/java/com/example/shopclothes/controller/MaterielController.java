package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.service.impl.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Material")
public class MaterielController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/hien-thi")
    public List<Material> hienThi(){
        return materialService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        materialService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        materialService.search(id);
    }

    @PostMapping("/add")
    public void add(Material material){
        materialService.save(material);
    }

    @PostMapping("/update/{id}")
    public void update(Material material, @PathVariable Long id){
        materialService.update(material, id);
    }
}
