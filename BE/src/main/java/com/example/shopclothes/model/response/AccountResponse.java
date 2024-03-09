package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long id;

    private String code;


    private String fullName;

    private LocalDate birthday;

    private Integer age;

    private LocalDateTime dateCreate;

    private LocalDateTime dateUpdate;

    private ComonEnum.GioiTinh sex;

    private String phoneNumber;

    private String email;

    private String avatar;

    private String city;

    private String district;

    private String wards;

    private String specificAddress;

    private String password;

    private ComonEnum.TrangThaiThuocTinh status;

    private Role role;

    public AccountResponse(Long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
