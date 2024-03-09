package com.example.shopclothes.controller;

import com.example.shopclothes.model.request.create_request.VoucherDetailCreateRequest;
import com.example.shopclothes.model.request.update_request.VoucherDetailUpdateRequest;
import com.example.shopclothes.service.VoucherDatailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/VocherDetail")
@Tag(name = "Vocher", description = "( Rest API Hiển thị, thêm, sửa, xóa VoucherDetails )")
@Validated


public class VocherDetailController {

    @Autowired
    private VoucherDatailService service;

    @GetMapping("/page")
    public ResponseEntity<?> getAllPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(service.getAllPage(page, pageSize));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllList(@RequestParam(name = "idVoucher",required = false)Long idVoucher) {
        return ResponseEntity.ok(service.findByVoucherId(idVoucher));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody List<VoucherDetailCreateRequest> requests) {
        return new ResponseEntity<>(service.addList(requests), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody List<VoucherDetailUpdateRequest> request) {
        service.update(request);
        return ResponseEntity.ok("Danh sách ChiTietSanPham đã được cập nhật thành công.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
