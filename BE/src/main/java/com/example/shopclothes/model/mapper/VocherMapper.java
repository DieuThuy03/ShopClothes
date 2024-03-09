package com.example.shopclothes.model.mapper;

import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.model.request.create_request.VocherCreateRequest;
import com.example.shopclothes.model.request.update_request.VocherUpdateRequest;
import com.example.shopclothes.model.response.VocherResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VocherMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VocherResponse convertEntityToResponse(Vocher voucher) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(voucher, VocherResponse.class);
    }

    public Vocher convertCreateRequestToEntity(VocherCreateRequest createdVoucherRequest) {
        return modelMapper.map(createdVoucherRequest, Vocher.class);
    }

    public Vocher convertUpdateRequestToEntity(VocherUpdateRequest updateRequest, Vocher detail) {
        modelMapper.map(updateRequest, detail);
        return detail;
    }
}
