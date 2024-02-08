package com.example.shopclothes.controller;

import com.example.shopclothes.entity.User;
import com.example.shopclothes.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hien-thi/{status}")
    public List<User> hienThi(@PathVariable String status){
        return userService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void seaarch(@PathVariable Long id){
        userService.search(id);
    }

    @PostMapping("/add")
    public void add(User user){
        userService.save(user);
    }

    @PostMapping("/update/{id}")
    public void update(User user, @PathVariable Long id){
        userService.update(user, id);
    }
}
