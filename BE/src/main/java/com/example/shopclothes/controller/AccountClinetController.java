package com.example.shopclothes.controller;

import com.example.shopclothes.model.request.create_request.AccountCreateRequest;
import com.example.shopclothes.model.request.update_request.AccountUpdateRequest;
import com.example.shopclothes.model.response.AccountResponse;
import com.example.shopclothes.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Account-Client")
@Tag(name = "Account_Client", description = "( Rest API Hiển thị, thêm, sửa, xóa Account_Client )")
@Validated

public class AccountClinetController {

    @Autowired
    private AccountService service;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
            @RequestParam(value = "status", defaultValue = "", required = false) String status,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "sortField", defaultValue = "", required = false) String sorter,
            @RequestParam(value = "sortOrder", defaultValue = "", required = false) String sortOrder
    ) {
        return ResponseEntity.ok(service.getAllKhachHang(page, pageSize, sorter,sortOrder, sex, searchText, status));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllKhachHang(){
        return ResponseEntity.ok(service.getAllKhachHang1());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addKhachHang(@RequestBody AccountCreateRequest createTaiKhoanRequest) {
        return new ResponseEntity<>(service.addKhachHang(createTaiKhoanRequest), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AccountUpdateRequest request) {
        AccountResponse taiKhoan = service.updateKhachHang(id, request);
        return ResponseEntity.ok(taiKhoan);
    }

    @PutMapping("/inactive-account-client/{id}")
    public ResponseEntity<?> cancelVoucher(@PathVariable(name = "id") Long id) {
        service.updateStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        byte[] excelFile = service.exportExcelTaiKhoan1();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=khach_hang.xlsx");
        response.getOutputStream().write(excelFile);
    }
}
