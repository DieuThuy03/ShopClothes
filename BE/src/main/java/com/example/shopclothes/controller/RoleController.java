package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Role;
import com.example.shopclothes.model.request.create_request.RoleCreateRequest;
import com.example.shopclothes.model.request.update_request.RooleUpdateRequest;
import com.example.shopclothes.service.RoleService;
import com.example.shopclothes.service.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Role")
@Tag(name = "Role", description = "( Rest API Hiển thị, thêm, sửa, xóa Role )")
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "status", required = false) String status

    ) {
        return ResponseEntity.ok(roleService.getAll(page, pageSize,sortField,sortOrder, searchText, status));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody RoleCreateRequest request) {
        return new ResponseEntity<>(roleService.add(request), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,@RequestBody RooleUpdateRequest request) {
        return ResponseEntity.ok(roleService.update(id, request));
    }
    @PutMapping("/inactive-role/{id}")
    public ResponseEntity<?> inActive(@PathVariable(name = "id") Long id) {
        roleService.inActive(id);
        return ResponseEntity.noContent().build();
    }
  }
