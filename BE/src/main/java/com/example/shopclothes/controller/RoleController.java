package com.example.shopclothes.controller;


import com.example.shopclothes.entity.Role;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.impl.RoleServiceIPL;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Role")
@Tag(name = "Role", description = "( Rest API Hiển thị, thêm, sửa, xóa nhà cung cấp )")
@Validated

public class RoleController {

    @Autowired
    private RoleServiceIPL roleServiceIPL;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roleServiceIPL.getAllRole());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Role> producerPage = roleRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }


    @GetMapping("/hien-thi-page-search")
    public ResponseEntity<?> hienThiPageSearch(String key, Integer trangThai, @RequestParam (defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page,5);
        return ResponseEntity.ok(roleServiceIPL.pageSearchRole(key,trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Role vaiTro){
        String ma = "VT" + new Random().nextInt(100000);
        vaiTro.setCode(ma);
        vaiTro.setDateCreate(new Date());
        return ResponseEntity.ok(roleServiceIPL.add(vaiTro));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(roleServiceIPL.detail(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Role vaiTro) {
        Role c = roleServiceIPL.detail(id);

        if (c != null) {
            vaiTro.setId(id);
            vaiTro.setCode(c.getCode());
            vaiTro.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            vaiTro.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(roleServiceIPL.add(vaiTro));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Role nhaSanXuat) {
        try {
            Role existingProducer = roleRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(roleServiceIPL.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}