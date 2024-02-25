package com.example.shopclothes.controller;


import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.Size;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.SizeRepo;
import com.example.shopclothes.service.impl.SizeServiceIPL;
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
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Size")
@Tag(name = "Size", description = "( Rest API Hiển thị, thêm, sửa, xóa kích thước )")
@Validated

public class SizeController {

    @Autowired
    private SizeServiceIPL sizeServiceIPL;

    @Autowired
    private SizeRepo sizeRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sizeServiceIPL.getAllKichThuoc());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Size> sizePage = sizeRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(sizePage.getContent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Size kichThuoc){
        String ma = "KT" + new Random().nextInt(100000);
        kichThuoc.setCode(ma);
        kichThuoc.setDateCreate(new Date());
        return ResponseEntity.ok(sizeServiceIPL.add(kichThuoc));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(sizeServiceIPL.detal(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Size kichThuoc) {
        Size c = sizeServiceIPL.detal(id);

        if (c != null) {
            kichThuoc.setId(id);
            kichThuoc.setCode(c.getCode());
            kichThuoc.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            kichThuoc.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(sizeServiceIPL.add(kichThuoc));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Size kichThuoc) {
        try {
            Size existingSize = sizeRepo.findById(id).orElse(null);
            if (existingSize != null) {
                existingSize.setDateCreate(existingSize.getDateCreate());
                existingSize.setDateUpdate(new Date());
                existingSize.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(sizeServiceIPL.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
