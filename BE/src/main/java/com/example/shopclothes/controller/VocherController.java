package com.example.shopclothes.controller;


import com.example.shopclothes.model.request.create_request.VocherCreateRequest;
import com.example.shopclothes.model.request.update_request.VocherUpdateRequest;
import com.example.shopclothes.service.VocherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Vocher")
@Tag(name = "Vocher", description = "( Rest API Hiển thị, thêm, sửa, xóa Voucher )")
@Validated

public class VocherController {

    @Autowired
    private VocherService service;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(name = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime

    ) {
        return ResponseEntity.ok(service.getAll(page, pageSize, sortField, sortOrder, searchText,
                status, startTime, endTime).getContent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody VocherCreateRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody VocherUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.cancelVoucher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/code")
    public ResponseEntity<?> getOneMa(@RequestParam(name = "code") String code) {
        return ResponseEntity.ok(service.findByMa(code));
    }

    @GetMapping("/da-su-dung")
    public ResponseEntity<?> getDaSuDung(@RequestParam("idVou") Long idVoucher,
                                         @RequestParam("startTime") LocalDate startTime,
                                         @RequestParam("endTime") LocalDate endTime) {
        return ResponseEntity.ok(service.soLanDaSuDung(idVoucher, startTime, endTime));
    }

    @PutMapping("/cancel-voucher/{id}")
    public ResponseEntity<?> cancelVoucher(@PathVariable(name = "id") Long id) {
        service.cancelVoucher(id);
        return ResponseEntity.noContent().build();
    }
}
