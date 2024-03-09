package com.example.shopclothes.service.impl;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.VocherDetail;
import com.example.shopclothes.model.mapper.VoucherDatailMapper;
import com.example.shopclothes.model.request.create_request.VoucherDetailCreateRequest;
import com.example.shopclothes.model.request.update_request.VoucherDetailUpdateRequest;
import com.example.shopclothes.model.response.VoucherDetailResponse;
import com.example.shopclothes.model.response.VoucherDetailResponseMapping;
import com.example.shopclothes.repositories.VocherDetailRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.VoucherDatailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class VocherDetailServiceImpl implements VoucherDatailService {

    @Autowired
    private VocherDetailRepo repository;

    @Autowired
    private VoucherDatailMapper mapper;


    @Override
    public List<VoucherDetailResponse> getAllList(Long idVoucher) {
        List<VocherDetail> list = repository.getAllList(idVoucher);
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherDetailResponseMapping> findByVoucherId(Long idVoucher) {
        return repository.findByVoucherId(idVoucher);
    }

    @Override
    public Page<VoucherDetailResponse> getAllPage(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<VocherDetail> chiTietPage = repository.getAllPage(pageable);
        return chiTietPage.map(mapper::convertEntityToResponse);
    }

    @Override
    @Transactional
    public List<VoucherDetailResponse> addList(List<VoucherDetailCreateRequest> requests) {
        List<VocherDetail> list = new ArrayList<>();
        for (VoucherDetailCreateRequest request : requests) {
            VocherDetail voucherChiTiet = mapper.convertCreateRequestToEntity(request);
            voucherChiTiet.setStatus(ComonEnum.TrangThaiVoucherChiTiet.ACTIVE);
            list.add(voucherChiTiet);
        }
        repository.saveAll(list);
        return null;
    }

    @Override
    @Transactional
    public VoucherDetailResponse update(List<VoucherDetailUpdateRequest> updateRequests) {
        List<VocherDetail> newVoucherChiTietList = new ArrayList<>();
        List<VocherDetail> updatedVoucherChiTietList = new ArrayList<>();

        for (VoucherDetailUpdateRequest updateRequest : updateRequests) {
            if (updateRequest.getId() != null) {
                // If the DTO has an ID, it means it's an update
                Optional<VocherDetail> optional = repository.findById(updateRequest.getId());
                if (optional.isPresent()) {
                    VocherDetail existingVoucherChiTiet = optional.get();
                    updateRequest.setId(existingVoucherChiTiet.getId());
                    mapper.convertUpdateRequestToEntity(updateRequest, existingVoucherChiTiet);
                    updatedVoucherChiTietList.add(existingVoucherChiTiet);
                }
            } else if (updateRequest.getId() == null) {
                VocherDetail newVoucherChiTiet = new VocherDetail();
                newVoucherChiTiet.setStatus(ComonEnum.TrangThaiVoucherChiTiet.ACTIVE);
                mapper.convertUpdateRequestToEntity(updateRequest, newVoucherChiTiet);
                newVoucherChiTietList.add(newVoucherChiTiet);
            }
        }
        // Add new objects to the database
        if (!newVoucherChiTietList.isEmpty()) {
            repository.saveAll(newVoucherChiTietList);
        }

        // Update existing objects in the database
        if (!updatedVoucherChiTietList.isEmpty()) {
            repository.saveAll(updatedVoucherChiTietList);
        }

        // You might want to return an appropriate response here based on the operation
        return null;
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }
}
