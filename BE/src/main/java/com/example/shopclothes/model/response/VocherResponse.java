package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Bill;
import com.example.shopclothes.entity.VocherDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VocherResponse {

    private Long id;

    private String code;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal reducedValue;

    private BigDecimal minimumOrder;

    private BigDecimal minimize;

    private LocalDateTime dateCreate;

    private LocalDateTime dateUpdate;

    private Integer quantity;

    private String describe;

    private ComonEnum.TrangThaiVoucher status;

    private List<VocherDetail> vocherDetails;

    private List<Bill> bills;

}
