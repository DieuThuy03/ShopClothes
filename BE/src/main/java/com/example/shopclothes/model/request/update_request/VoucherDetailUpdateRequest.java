package com.example.shopclothes.model.request.update_request;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Vocher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherDetailUpdateRequest {

    private Long id;

    private Integer numberOfUses;

    private ComonEnum.TrangThaiThuocTinh status;

    private Vocher voucher;

}
