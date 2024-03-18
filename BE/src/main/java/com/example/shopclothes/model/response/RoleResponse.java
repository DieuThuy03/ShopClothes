package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RoleResponse {
    private Long id;

    private String name;

    private Date dateCreate;

    private Date dateUpdate;

    private ComonEnum.TrangThaiThuocTinh status;
}
