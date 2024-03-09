package com.example.shopclothes.model.request.create_request;


import com.example.shopclothes.entity.Vocher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherDetailCreateRequest {

    private Integer numberOfUses;


    private Vocher voucher;
}
