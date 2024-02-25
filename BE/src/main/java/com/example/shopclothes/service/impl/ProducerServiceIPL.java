package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ProducerRepo;

import com.example.shopclothes.service.ProducerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ProducerServiceIPL implements ProducerServices {

    @Autowired
    private ProducerRepo producerRepo;

    @Override
    public List<Producer> getAllNSX() {
        return producerRepo.getAll();
    }

    @Override
    public Page<Producer> pageNSX(Pageable pageable) {
        return producerRepo.findAll(pageable);
    }

    @Override
    public Page<Producer> pageSearchNSX(String key, Integer trangThai, Pageable pageable) {
        return producerRepo.searchPageNSX(key, trangThai, pageable);
    }

    @Override
    public Producer add(Producer nhaSanXuat) {
        nhaSanXuat.setName(nhaSanXuat.getName());
        return producerRepo.save(nhaSanXuat);
    }
    @Override
    public Producer detail(Long id) {
        return producerRepo.findById(id).orElse(null);
    }

    @Override
    public Producer xoa(Long id) {
        Producer nhaSanXuat = producerRepo.findById(id).orElse(null);
        if (nhaSanXuat != null) {
            nhaSanXuat.setDateCreate(nhaSanXuat.getDateCreate());
            nhaSanXuat.setDateUpdate(new Date());
            nhaSanXuat.setStatus(Status.NGUNG_HOAT_DONG);
            return producerRepo.save(nhaSanXuat);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }

}
