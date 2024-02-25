package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.Role;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceIPL implements RoleService {

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public List<Role> getAllRole() {
        return roleRepo.getAll();
    }

    @Override
    public Page<Role> pageRole(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }

    @Override
    public Page<Role> pageSearchRole(String key, Integer status, Pageable pageable) {
        return roleRepo.searchRole(key, status, pageable);
    }

    @Override
    public Role add(Role vaiTro) {
        vaiTro.setName(vaiTro.getName());
        return roleRepo.save(vaiTro);
    }

    @Override
    public Role detail(Long id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    public Role xoa(Long id) {
        Role vaiTro = roleRepo.findById(id).orElse(null);
        if (vaiTro != null) {
            vaiTro.setDateCreate(vaiTro.getDateCreate());
            vaiTro.setDateUpdate(new Date());
            vaiTro.setStatus(Status.NGUNG_HOAT_DONG);
            return roleRepo.save(vaiTro);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }
}
