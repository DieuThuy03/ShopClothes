package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.Size;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.MaterielRepo;
import com.example.shopclothes.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialServiceIPL implements MaterialService {

    @Autowired
    private MaterielRepo materielRepo;

    @Override
    public List<Material> getAllChatLieu() {
        return materielRepo.getAll();
    }

    @Override
    public Page<Material> pageChatLieu(Pageable pageable) {
        return materielRepo.findAll(pageable);
    }

    @Override
    public Page<Material> pageSearchChatLieu(String key, Integer trangThai, Pageable pageable) {
        return materielRepo.searchPageChatLieu(key, trangThai, pageable);
    }

    @Override
    public Material add(Material chatLieu) {
        chatLieu.setName(chatLieu.getName());
        return materielRepo.save(chatLieu);
    }

    @Override
    public Material detail(Long id) {
        return materielRepo.findById(id).orElse(null);
    }

    @Override
    public Material xoa(Long id) {
        Material chatLieu = materielRepo.findById(id).orElse(null);
        if (chatLieu != null){
            chatLieu.setDateCreate(chatLieu.getDateCreate());
            chatLieu.setDateUpdate(new Date());
            chatLieu.setStatus(Status.NGUNG_HOAT_DONG);
            return materielRepo.save(chatLieu);
        }
        return null;
    }
}


