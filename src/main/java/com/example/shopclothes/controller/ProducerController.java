package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.repositories.ProducerRepo;
import com.example.shopclothes.service.impl.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Producer")

public class ProducerController {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private ProducerRepo producerRepo;

    @GetMapping("/hien-thi")
    public List<Producer> hienThi(){
        return producerService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        producerService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        producerService.search(id);
    }

    @PostMapping("/add")
    public void add(Producer producer){
        producerRepo.save(producer);
    }

    @PostMapping("/update/{id}")
    public void update(Producer producer, @PathVariable Long id){
        producerService.update(producer,id);
    }
}
