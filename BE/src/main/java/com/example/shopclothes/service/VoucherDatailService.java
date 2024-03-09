package com.example.shopclothes.service;

import com.example.shopclothes.model.request.create_request.VoucherDetailCreateRequest;
import com.example.shopclothes.model.request.update_request.VoucherDetailUpdateRequest;
import com.example.shopclothes.model.response.VoucherDetailResponse;
import com.example.shopclothes.model.response.VoucherDetailResponseMapping;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoucherDatailService {

    List<VoucherDetailResponse> getAllList(Long idVoucher);

    List<VoucherDetailResponseMapping> findByVoucherId(Long idVoucher);


    Page<VoucherDetailResponse> getAllPage(Integer page, Integer pageSize);

    List<VoucherDetailResponse> addList(List<VoucherDetailCreateRequest> requests);

    VoucherDetailResponse update(List<VoucherDetailUpdateRequest> updateRequests);

    void delete(Long id);
}
