package com.example.shopclothes.model.request.create_request;

import com.example.shopclothes.common.ComonEnum;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class RoleCreateRequest {
    private Long id;

    private String name;

    private Date dateCreate;

    private Date dateUpdate;

    private ComonEnum.TrangThaiThuocTinh status;
}
