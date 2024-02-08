package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.service.impl.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coler")

public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/hien-thi/{status}")
    public List<Color> hienThi(@PathVariable String status){
        return colorService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable  Long id){
        colorService.delete(id);
    }

    @PostMapping("/add")
    public void add(Color color){
        colorService.save(color);
    }

    @PostMapping("/update/{id}")
    public void update(Color color,@PathVariable Long id){
        colorService.update(color, id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        colorService.search(id);
    }
}
