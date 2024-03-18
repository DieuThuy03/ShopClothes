package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Account;
import com.example.shopclothes.model.request.create_request.AccountCreateRequest;
import com.example.shopclothes.model.request.update_request.AccountUpdateRequest;
import com.example.shopclothes.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Account")
@Tag(name = "Account", description = "( Rest API Hiển thị, thêm, sửa, xóa Account )")
@Validated

public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "sex", required = false) String sex,
            @RequestParam(name = "status", required = false) String status

    ) {
        return ResponseEntity.ok(service.getAll(page, pageSize,sortField,sortOrder, searchText,sex, status));
    }

    @GetMapping("/abc")
    public ResponseEntity<?> getAll12() {
        return ResponseEntity.ok(service.getAllTaiKhoan());
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AccountCreateRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,@RequestBody AccountUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll11(@RequestParam("email")String email) {
        return ResponseEntity.ok(service.getAllTaiKhoan(email));
    }

    @GetMapping("/excel/nv")
    public void exportExcel(HttpServletResponse response) throws IOException {
        byte[] excelFile = service.exportExcelTaiKhoan();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=nhan_vien.xlsx");
        response.getOutputStream().write(excelFile);
    }

    @PutMapping("/inactive-account/{id}")
    public ResponseEntity<?> cancelVoucher(@PathVariable(name = "id") Long id) {
        service.updateStatus(id);
        return ResponseEntity.noContent().build();
    }
}
