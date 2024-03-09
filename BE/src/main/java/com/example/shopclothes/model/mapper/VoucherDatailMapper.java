package com.example.shopclothes.model.mapper;

import com.example.shopclothes.entity.VocherDetail;
import com.example.shopclothes.model.request.create_request.VoucherDetailCreateRequest;
import com.example.shopclothes.model.request.update_request.VoucherDetailUpdateRequest;
import com.example.shopclothes.model.response.VoucherDetailResponse;
import com.example.shopclothes.model.response.VoucherDetailResponseMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoucherDatailMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VoucherDetailResponse convertEntityToResponse(VocherDetail voucher) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(voucher, VoucherDetailResponse.class);
    }

    public VocherDetail convertCreateRequestToEntity(VoucherDetailCreateRequest request) {
        return modelMapper.map(request, VocherDetail.class);
    }

    public VocherDetail convertUpdateRequestToEntity(VoucherDetailUpdateRequest request, VocherDetail detail) {
        modelMapper.map(request, detail);
        return detail;
    }
}
