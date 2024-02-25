package com.example.shopclothes.service;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRole();

    public Page<Role> pageRole(Pageable pageable);

    public Page<Role> pageSearchRole(String key,Integer status, Pageable pageable);

    public Role add(Role vaiTro);

    public Role detail(Long id);

    public Role xoa( Long id);
}
