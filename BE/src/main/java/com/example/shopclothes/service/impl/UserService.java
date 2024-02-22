package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.User;
import com.example.shopclothes.repositories.UserRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService implements IService<User> {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User object) {
        userRepo.save(object);
    }

    @Override
    public void update(User object, Long id) {
        userRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        userRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        userRepo.findById(id);
    }

    @Override
    public List<User> select() {
        return userRepo.findAll();
    }
}
