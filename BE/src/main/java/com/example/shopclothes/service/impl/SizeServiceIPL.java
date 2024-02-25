package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.Size;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.SizeRepo;
import com.example.shopclothes.service.SizeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SizeServiceIPL implements SizeServices {

    @Autowired
    private SizeRepo sizeRepo;

    @Override
    public List<Size> getAllKichThuoc() {
        return sizeRepo.getAll();
    }

    @Override
    public Page<Size> pageKichThuoc(Pageable pageable) {
        return sizeRepo.findAll(pageable);
    }

    @Override
    public Page<Size> pageSearchKichThuoc(String key, Integer trangThai, Pageable pageable) {
        return sizeRepo.searchPageKichThuoc(key, trangThai, pageable);
    }

    @Override
    public Size add(Size kichThuoc) {
        kichThuoc.setName(kichThuoc.getName());
        return sizeRepo.save(kichThuoc);
    }

    @Override
    public Size detal(Long id) {
        return sizeRepo.findById(id).orElse(null);
    }

    @Override
    public Size xoa(Long id) {
        Size kichThuoc = sizeRepo.findById(id).orElse(null);
        if (kichThuoc != null) {
            kichThuoc.setDateCreate(kichThuoc.getDateCreate());
            kichThuoc.setDateUpdate(new Date());
            kichThuoc.setStatus(Status.NGUNG_HOAT_DONG);
            return sizeRepo.save(kichThuoc);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }
}
