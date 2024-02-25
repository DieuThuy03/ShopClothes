package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.MaterielRepo;
import com.example.shopclothes.repositories.ProducerRepo;
import com.example.shopclothes.service.impl.MaterialServiceIPL;
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
@RequestMapping("/api/v1/Material")
@Tag(name = "Material", description = "( Rest API Hiển thị, thêm, sửa, xóa nhà cung cấp )")
@Validated
public class MaterielController {

    @Autowired
    private MaterialServiceIPL materialServiceIPL;
    @Autowired
    private MaterielRepo materielRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(materialServiceIPL.getAllChatLieu());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Material> producerPage = materielRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }


    @GetMapping("/hien-thi-page-search")
    public ResponseEntity<?> hienThiPageSearch(String key, Integer trangThai, @RequestParam (defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page,5);
        return ResponseEntity.ok(materialServiceIPL.pageSearchChatLieu(key,trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Material chatLieu){
        String ma = "CL" + new Random().nextInt(100000);
        chatLieu.setCode(ma);
        chatLieu.setDateCreate(new Date());
        return ResponseEntity.ok(materialServiceIPL.add(chatLieu));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(materialServiceIPL.detail(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Material chatLieu) {
        Material c = materialServiceIPL.detail(id);

        if (c != null) {
            chatLieu.setId(id);
            chatLieu.setCode(c.getCode());
            chatLieu.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            chatLieu.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(materialServiceIPL.add(chatLieu));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Material chatLieu) {
        try {
            Material existingProducer = materielRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(materialServiceIPL.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
