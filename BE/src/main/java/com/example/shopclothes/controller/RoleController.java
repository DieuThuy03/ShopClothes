package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Role;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Role")

public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/hien-thi")
    public List<Role> hienThi(){
        return roleService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        roleService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        roleService.search(id);
    }

    @PostMapping("/add")
    public void add(Role role){
        roleService.save(role);
    }

    @PostMapping("/update/{id}")
    public void update(Role role, @PathVariable Long id){
        roleService.update(role, id);
    }
}
