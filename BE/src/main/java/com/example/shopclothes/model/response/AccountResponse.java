package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long id;

    private String fullName;

    private String citizenIdentificationCard;

    private LocalDate birthday;

    private ComonEnum.GioiTinh sex;

    private String phoneNumber;

    private String email;

    private String city;

    private String district; // quan/huyen

    private String wards; // phuong/ xa

    private String specificAddress; //dai

    private String avatar;

    private String password;

    private Date dateCreate;

    private Date dateUpdate;

    private ComonEnum.TrangThaiThuocTinh status;

    private Role role;

    public AccountResponse(Long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
