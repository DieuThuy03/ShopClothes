package com.example.shopclothes.service;

import com.example.shopclothes.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaterialService {

    public List<Material> getAllChatLieu();

    public Page<Material> pageChatLieu(Pageable pageable);

    public Page<Material> pageSearchChatLieu(String key, Integer trangThai, Pageable pageable);

    public Material add(Material chatLieu);

    public Material detail(Long id);

    public Material xoa(Long id);
}
