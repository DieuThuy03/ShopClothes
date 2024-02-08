package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Role;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleService implements IService<Role> {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void save(Role object) {
        roleRepo.save(object);
    }

    @Override
    public void update(Role object, Long id) {
        roleRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        roleRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        roleRepo.findById(id).orElse(null);
    }

    @Override
    public List<Role> select(String status) {
        return roleRepo.findAll();
    }
}
