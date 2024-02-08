package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Address;
import com.example.shopclothes.repositories.AddressRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AddressService implements IService<Address> {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public void save(Address object) {
        addressRepo.save(object);
    }

    @Override
    public void update(Address object, Long id) {
        addressRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        addressRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        addressRepo.findById(id).orElse(null);
    }

    @Override
    public List<Address> select(String status) {
        return addressRepo.findAll();
    }
}
