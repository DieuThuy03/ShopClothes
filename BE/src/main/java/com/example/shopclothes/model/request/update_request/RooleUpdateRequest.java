package com.example.shopclothes.model.request.update_request;

import com.example.shopclothes.common.ComonEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class RooleUpdateRequest {
    private Long id;

    private String name;

    private Date dateCreate;

    private Date dateUpdate;

    private ComonEnum.TrangThaiThuocTinh status;

    private boolean INACTIVE;
}
