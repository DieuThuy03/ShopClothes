package com.example.shopclothes.service;


import com.example.shopclothes.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeServices {

    public List<Size> getAllKichThuoc();

    public Page<Size> pageKichThuoc(Pageable pageable);

    public Page<Size> pageSearchKichThuoc(String key, Integer trangThai, Pageable pageable);

    public Size add(Size kichThuoc);

    public Size detal(Long id);

    public Size xoa(Long id);

}
