package com.example.shopclothes.service;

import com.example.shopclothes.model.request.create_request.VocherCreateRequest;
import com.example.shopclothes.model.request.update_request.VocherUpdateRequest;
import com.example.shopclothes.model.response.VocherResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VocherService {

    Page<VocherResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,
                                String searchText, String status,
                                LocalDateTime startTime, LocalDateTime endTime);

    List<VocherResponse> getListVoucher(Long id);

    List<VocherResponse> getListVoucherSuDung(Long id);

    VocherResponse add(VocherCreateRequest request);

    VocherResponse update(Long id, VocherUpdateRequest request);

    VocherResponse delete(Long id);

    Long soLanDaSuDung(Long idVoucher, LocalDate startTime, LocalDate end);


    VocherResponse findById(Long id);

    VocherResponse findByMa(String code);

    void updateVoucherStatus();

    void cancelVoucher(Long id);
}
