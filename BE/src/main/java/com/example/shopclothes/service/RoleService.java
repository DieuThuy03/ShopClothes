package com.example.shopclothes.service;

import com.example.shopclothes.model.request.create_request.RoleCreateRequest;
import com.example.shopclothes.model.request.update_request.RooleUpdateRequest;
import com.example.shopclothes.model.response.RoleResponse;
import org.springframework.data.domain.Page;

public interface RoleService {
    Page<RoleResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String status);

    RoleResponse add(RoleCreateRequest request);

    RoleResponse update(Long id, RooleUpdateRequest request);

    void  delete(Long id);

    RoleResponse findById(Long id);

    void inActive (Long id);
}
