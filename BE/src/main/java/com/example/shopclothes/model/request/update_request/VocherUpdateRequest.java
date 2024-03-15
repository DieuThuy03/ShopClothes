package com.example.shopclothes.model.request.update_request;

import com.example.shopclothes.common.ComonEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class VocherUpdateRequest {

    private  Long id;

    //private String code;

    private String name;


    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime startTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime endTime;


    private BigDecimal minimumOrder;

    private BigDecimal reducedValue;

    private BigDecimal Minimize;

    private Integer quantity;

    private String describe;

    @Enumerated(EnumType.STRING)
    private ComonEnum.TrangThaiVoucher status;

    private boolean cancelled;
}
