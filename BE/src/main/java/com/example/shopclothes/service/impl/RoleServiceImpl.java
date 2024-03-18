package com.example.shopclothes.service.impl;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Role;
import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.model.mapper.RoleMapper;
import com.example.shopclothes.model.request.create_request.RoleCreateRequest;
import com.example.shopclothes.model.request.update_request.RooleUpdateRequest;
import com.example.shopclothes.model.response.RoleResponse;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RoleMapper mapper;

    @Override
    public Page<RoleResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String status) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("dateCreate").descending();
        }

        ComonEnum.TrangThaiThuocTinh trangThai;

        if (status == null || status.equals("")) {
            trangThai = null;
        } else {
            trangThai = ComonEnum.TrangThaiThuocTinh.valueOf(status);
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Role> vaiTroPage = roleRepo.findByAll(pageable, searchText,trangThai);
        return vaiTroPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public RoleResponse add(RoleCreateRequest request) {
        Role createdRole = mapper.convertCreateRequestToEntity(request);
        createdRole.setName("CUSTOMER");
        createdRole.setStatus(ComonEnum.TrangThaiThuocTinh.ACTIVE);
        Role savedVaiTro = this.roleRepo.save(createdRole);
        return mapper.convertEntityToResponse(savedVaiTro);

    }

    @Override
    public RoleResponse update(Long id, RooleUpdateRequest request) {
        Optional<Role> optional = roleRepo.findById(id);
        Role vaiTro = optional.get();
        mapper.convertUpdateRequestToEntity(request,vaiTro);
        return mapper.convertEntityToResponse(roleRepo.save(vaiTro));

    }

    @Override
    public void delete(Long id) {
        Optional<Role> optional = this.roleRepo.findById(id);
        roleRepo.delete(optional.get());
    }

    @Override
    public RoleResponse findById(Long id) {
        Optional<Role> optional = roleRepo.findById(id);
        return mapper.convertEntityToResponse(optional.get());

    }

    @Override
    public void inActive(Long id) {
        Role role = roleRepo.findById(id).orElse(null);
        role.setStatus(ComonEnum.TrangThaiThuocTinh.INACTIVE);
        roleRepo.save(role);
    }
}
