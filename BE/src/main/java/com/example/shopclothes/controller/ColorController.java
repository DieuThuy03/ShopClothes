package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.service.impl.ColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/colors")

public class ColorController {

    @Autowired
    private ColorServiceImpl colorService;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> hienThi(@RequestParam(defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return ResponseEntity.ok(colorService.select(Status.DANG_HOAT_DONG, pageable));
//        return colorService.select();
    }

//    @GetMapping("/hien-thi")
//    public List<Color> hienThi(){
//        return colorService.select();
//    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable  Long id){
        colorService.delete(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Color color){
        String code = "CL" +  new Random().nextInt(100000);
        color.setCode(code);
        color.setDateCreate(new Date());
        colorService.save(color);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Color color,@PathVariable Long id){
        colorService.update(color, id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        colorService.search(id);
    }

}
