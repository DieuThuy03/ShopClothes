package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Vocher;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class VoucherDetailResponse {

    private Long id;
    private Integer numberOfUses;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;
    private ComonEnum.TrangThaiThuocTinh status;
    private Vocher voucher;
}
