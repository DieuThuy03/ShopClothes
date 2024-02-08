package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Address;
import com.example.shopclothes.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")

public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/hien-thi/{status}")
    public List<Address> hienThi(@PathVariable String status) {
        return addressService.select(status);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id) {
        addressService.search(id);
    }

    @PostMapping("/add")
    public void add(Address address) {
        addressService.save(address);
    }

    @PostMapping("/update/{id}")
    public void update(Address address, @PathVariable Long id){
        addressService.update(address, id);
    }

}
